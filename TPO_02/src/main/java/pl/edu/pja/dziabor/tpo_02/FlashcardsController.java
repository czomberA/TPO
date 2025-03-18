package pl.edu.pja.dziabor.tpo_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.pja.dziabor.tpo_02.DisplayModes.IDisplayMode;

import java.util.*;

@Controller
public class FlashcardsController {

    private IFCService flashcardService;
    private IDisplayMode displayMode;
    private Scanner scanner;

    @Autowired
    public FlashcardsController(IFCService flashcardService, IDisplayMode displayMode, Scanner scanner) {
        this.flashcardService = flashcardService;
        this.displayMode = displayMode;
        this.scanner = scanner;
    }

    public void run() {
        flashcardService.readEntries();
        boolean exit = false;
        System.out.println("Please enter a number from 1 to 4 to select an option.");
        while (!exit) {
            System.out.println("~~MENU~~\n1. Add new word to the dictionary\n2. Display all words\n3. Test \n4. Exit\nSelect option: ");
            String menu = scanner.nextLine(); //Very primitive error handling. Using string, so that whatever is entered doesn't throw exception, instead goes to default branch
            switch (menu) {
                case "1":
                    addNewEntry();
                    break;
                case "2":
                    displayAll();
                    break;
                case "3":
                    testRandom();
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 4.");
                    break;
            }
        }
    }

    private void testRandom() {
        Entry random = flashcardService.getRandomEntry();
        int fistLanguage = (int )(Math.random() * 3); //Randomly chooses one of the languages
        String word;
        switch (fistLanguage) {
            case 0: //English
                word = displayMode.changeEntryDisplay(random.getEnglish());
                System.out.printf("Your word is %s%n", word);
                germanTest(random);
                polishTest(random);
                break;
            case 1: //German
                word = displayMode.changeEntryDisplay(random.getGerman());
                System.out.printf("Your word is %s%n", word);
                englishTest(random);
                polishTest(random);
                break;
            case 2: //Polish
                word = displayMode.changeEntryDisplay(random.getPolish());
                System.out.printf("Your word is %s%n", word);
                englishTest(random);
                germanTest(random);
                break;
        }
    }

    private void englishTest(Entry random) {
        System.out.println("English: ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(random.getEnglish())){
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong! Correct answer is " + displayMode.changeEntryDisplay(random.getEnglish()));
        }
    }

    private void polishTest(Entry random) {
        System.out.println("Polish: ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(random.getPolish())) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong! Correct answer is " + displayMode.changeEntryDisplay(random.getPolish()));
        }
    }

    private void germanTest(Entry random) {
        System.out.println("German: ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(random.getGerman())){
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong! Correct answer is " + displayMode.changeEntryDisplay(random.getGerman()));
        }
    }

    private void displayAll() {
        displayMode.displayEntries(flashcardService.getAllEntries());
    }

    private void addNewEntry() {
        System.out.println("Enter english word: ");
        String e = scanner.nextLine();
        System.out.println("Enter german word: ");
        String g = scanner.nextLine();
        System.out.println("Enter polish word: ");
        String p = scanner.nextLine();
        if (e.isEmpty() || g.isEmpty() || p.isEmpty()) {
            System.out.println("At least one word is not provided. Please try again.");
            return;
        }
        flashcardService.addNewEntry(e, g, p);
        System.out.printf("New entry added (%s-%s-%s)%n", displayMode.changeEntryDisplay(e), displayMode.changeEntryDisplay(g), displayMode.changeEntryDisplay(p));
    }
}
