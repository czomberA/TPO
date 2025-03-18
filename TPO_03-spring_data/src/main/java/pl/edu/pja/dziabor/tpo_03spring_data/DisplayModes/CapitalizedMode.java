package pl.edu.pja.dziabor.tpo_03spring_data.DisplayModes;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.pja.dziabor.tpo_03spring_data.Entry;


import java.util.List;

@Service
@Profile("Capitalized")
public class CapitalizedMode implements IDisplayMode{
    @Override
    public void displayEntries(List<Entry> allEntries) {
        if (allEntries.isEmpty()) {
            System.out.println("No entries found");
            return;
        }
        System.out.printf("%-10s%-10s%-10s%n", "English".toUpperCase(), "German".toUpperCase(), "Polish".toUpperCase());
        for (Entry entry : allEntries) {
            System.out.printf("%-10s%-10s%-10s%n", entry.getEnglish().toUpperCase(), entry.getGerman().toUpperCase(), entry.getPolish().toUpperCase());
        }
        System.out.println();
    }

    @Override
    public String changeEntryDisplay(String random) {

        return random.toUpperCase();
    }
}
