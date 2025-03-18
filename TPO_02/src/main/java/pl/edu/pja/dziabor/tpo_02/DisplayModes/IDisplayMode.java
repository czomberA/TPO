package pl.edu.pja.dziabor.tpo_02.DisplayModes;

import pl.edu.pja.dziabor.tpo_02.Entry;

import java.util.List;

public interface IDisplayMode {
    void displayEntries(List<Entry> allEntries);
    String changeEntryDisplay(String random);
}
