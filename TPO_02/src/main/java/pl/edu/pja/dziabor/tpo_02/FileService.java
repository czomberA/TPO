package pl.edu.pja.dziabor.tpo_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(";");
                if (words.length == 3) {
                    Entry entry = new Entry(words[0].trim(), words[1].trim(), words[2].trim());
                    entryRepository.addEntry(entry);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Entry prepareEntry(String english, String german, String polish) {
        return new Entry(english, german, polish);
    }

    @Override
    public void addNewEntry(String english, String german, String polish) {
        Entry newEntry = prepareEntry(english, german, polish);
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filePath), true))) {
            pw.println(String.format("%s;%s;%s", english, german, polish));
            entryRepository.addEntry(newEntry);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.getEntries();
    }

    @Override
    public Entry getRandomEntry() {
        Random random = new Random();
        return getAllEntries().get(random.nextInt(getAllEntries().size()));
    }
}
