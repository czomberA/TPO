package pl.edu.pja.dziabor.tpo_03entitymanager.DisplayModes;



import pl.edu.pja.dziabor.tpo_03entitymanager.Entry;

import java.util.List;

public interface IDisplayMode {
    void displayEntries(List<Entry> allEntries);
    String changeEntryDisplay(String random);
}
