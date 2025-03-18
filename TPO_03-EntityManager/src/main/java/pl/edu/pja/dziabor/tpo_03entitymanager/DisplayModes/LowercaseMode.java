package pl.edu.pja.dziabor.tpo_03entitymanager.DisplayModes;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.pja.dziabor.tpo_03entitymanager.Entry;


import java.util.List;

@Service
@Profile("Lowercase")
public class LowercaseMode implements IDisplayMode{

    @Override
    public void displayEntries(List<Entry> allEntries) {
        if (allEntries.isEmpty()) {
            System.out.println("No entries found");
            return;
        }
        System.out.printf("%-10s%-10s%-10s%n", "English".toLowerCase(), "German".toLowerCase(), "Polish".toLowerCase());
        for (Entry entry : allEntries) {
            System.out.printf("%-10s%-10s%-10s%n", entry.getEnglish().toLowerCase(), entry.getGerman().toLowerCase(), entry.getPolish().toLowerCase());
        }
        System.out.println();
    }

    @Override
    public String changeEntryDisplay(String random) {

        return random.toLowerCase();
    }
}
