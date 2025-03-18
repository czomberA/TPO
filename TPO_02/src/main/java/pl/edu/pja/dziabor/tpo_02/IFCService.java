package pl.edu.pja.dziabor.tpo_02;


import java.util.List;


public interface IFCService {
    void readEntries();

    void addNewEntry(String e, String g, String p);

    List<Entry> getAllEntries();

    Entry getRandomEntry();
}
