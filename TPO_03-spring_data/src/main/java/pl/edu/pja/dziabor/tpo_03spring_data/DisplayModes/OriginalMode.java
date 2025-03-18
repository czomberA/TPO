package pl.edu.pja.dziabor.tpo_03spring_data.DisplayModes;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.pja.dziabor.tpo_03spring_data.Entry;


import java.util.List;

@Service
@Primary
@Profile("Original")
public class OriginalMode implements IDisplayMode{
    @Override
    public void displayEntries(List<Entry> allEntries) {
        if (allEntries.isEmpty()) {
            System.out.println("No entries found");
            return;
        }
        System.out.printf("%-10s%-10s%-10s%n", "English", "German", "Polish");
        for (Entry entry : allEntries) {
            System.out.printf("%-10s%-10s%-10s%n", entry.getEnglish(), entry.getGerman(), entry.getPolish());
        }
        System.out.println();
    }

    @Override
    public String changeEntryDisplay(String random) {

        return random;
    }
}
