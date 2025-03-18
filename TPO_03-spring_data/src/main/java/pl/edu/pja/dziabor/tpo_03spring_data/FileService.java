package pl.edu.pja.dziabor.tpo_03spring_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class FileService implements IFCService {
    String filePath;
    SpringDataEntryRepository entryRepository;

    @Autowired
    public FileService(SpringDataEntryRepository entryRepository, @Value("${pl.edu.pja.tpo02.filename}") String filename) {
        this.entryRepository = entryRepository;
        this.filePath = filename;
    }

    @Override
    public void readEntries() {

        int id = 1;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(";");
                if (words.length == 3) {
                    Entry entry = new Entry(id, words[0].trim(), words[1].trim(), words[2].trim());
                    entryRepository.save(entry);
                    id++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Entry prepareEntry(int id, String english, String german, String polish) {
        return new Entry(id, english, german, polish);
    }

    @Override
    public void addNewEntry(int id, String english, String german, String polish) {
        Entry newEntry = prepareEntry(id, english, german, polish);
        entryRepository.save(newEntry);

    }

    @Override
    public List<Entry> getAllEntries() {
        return (List<Entry>) entryRepository.findAll();

    }

    @Override
    public Entry getRandomEntry() {
        Random random = new Random();
        List<Entry> entries = getAllEntries();
        return entries.get(random.nextInt(entries.size()));
    }


    @Override
    public List<Entry> getAllEntries(String language, String order) {
        List<Entry> all = (List<Entry>) entryRepository.findAll();
        String lang = "";
        String ord = "";
        if (language.equalsIgnoreCase("e") && order.equalsIgnoreCase("a")) {
            all.sort(Comparator.comparing(Entry::getEnglish));
        } else if (language.equalsIgnoreCase("e") && order.equalsIgnoreCase("d")) {
            all.sort(Comparator.comparing(Entry::getEnglish).reversed());
        }

        if (language.equalsIgnoreCase("g") && order.equalsIgnoreCase("a")) {
            all.sort(Comparator.comparing(Entry::getGerman));
        } else if (language.equalsIgnoreCase("g") && order.equalsIgnoreCase("d")) {
            all.sort(Comparator.comparing(Entry::getGerman).reversed());
        }

        if (language.equalsIgnoreCase("p") && order.equalsIgnoreCase("a")) {
            all.sort(Comparator.comparing(Entry::getPolish));
        } else if (language.equalsIgnoreCase("p") && order.equalsIgnoreCase("d")) {
            all.sort(Comparator.comparing(Entry::getPolish).reversed());
        }
        return all;


    }

    @Override
    public Optional<Entry> findById(Long id) {
        return entryRepository.findById(id);
    }

    @Override
    public void modifyFound(long id, String english, String german, String polish) {

        Entry entry = prepareEntry((int) id, english, german, polish);
        entryRepository.save(entry);
    }

    @Override
    public void deleteEntry(int answer) {
        entryRepository.deleteById((long) answer);
    }
}
