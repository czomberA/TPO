package pl.edu.pja.dziabor.tpo_03entitymanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Service
public class FileService implements IFCService {
    String filePath;
    EntryRepository entryRepository;

    @Autowired
    public FileService(EntryRepository entryRepository, @Value("${pl.edu.pja.tpo02.filename}") String filename) {
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
                    entryRepository.addEntry(entry);
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
        entryRepository.addEntry(newEntry);

    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.getEntries();
    }

    @Override
    public Entry getRandomEntry() {
        Random random = new Random();
        List<Entry> entries = getAllEntries();
        return entries.get(random.nextInt(entries.size()));
    }

    public EntryRepository getEntryRepository() {
        return entryRepository;
    }

    @Override
    public List<Entry> getAllEntries(String language, String order) {

        return entryRepository.getEntries(language, order);

    }

    @Override
    public Optional<Entry> findById(Long id) {
        return entryRepository.findById(id);
    }

    @Override
    public void modifyFound(long id, String english, String german, String polish) {
        entryRepository.updateEntry(id, english, german, polish);
    }

    @Override
    public void deleteEntry(int answer) {
        entryRepository.deleteById((long) answer);
    }
}
