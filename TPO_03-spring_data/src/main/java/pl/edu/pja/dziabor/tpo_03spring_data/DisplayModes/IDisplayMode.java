package pl.edu.pja.dziabor.tpo_03spring_data.DisplayModes;





import pl.edu.pja.dziabor.tpo_03spring_data.Entry;

import java.util.List;

public interface IDisplayMode {
    void displayEntries(List<Entry> allEntries);
    String changeEntryDisplay(String random);
}
