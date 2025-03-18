package pl.edu.pja.dziabor.tpo_03spring_data;

import java.util.List;
import java.util.Optional;


public interface IFCService {
    void readEntries();

    void addNewEntry(int id, String e, String g, String p);

    List<Entry> getAllEntries();

    Entry getRandomEntry();


    List<Entry> getAllEntries(String language, String order);

    Optional<Entry> findById (Long id);

    void modifyFound(long id, String answer3, String trim, String trimmed);

    void deleteEntry(int answer);
}
