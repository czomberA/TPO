package pl.edu.pja.dziabor.tpo_03entitymanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.pja.dziabor.tpo_03entitymanager.DisplayModes.IDisplayMode;

import java.util.*;


@Controller
public class FlashcardsController {
    private final IFCService flashcardService;
    private final IDisplayMode displayMode;
    private final Scanner scanner;

    @Autowired
    public FlashcardsController(IFCService flashcardService, IDisplayMode displayMode, Scanner scanner) {
        this.flashcardService = flashcardService;
        this.displayMode = displayMode;
        this.scanner = scanner;
    }


    public void run() {
        if (!flashcardService.getEntryRepository().isDatabasePopulated()) {
            flashcardService.readEntries();
        }
        boolean exit = false;
        System.out.println("Please enter a number from 1 to 5 to select an option.");
        while (!exit) {
            System.out.println("~~MENU~~\n1. Add new word to the dictionary\n2. Display all words\n3. Test \n4. Find word (and delete/modify)\n5. Exit\nSelect option: ");
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
                    searchForWord();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 5.");
                    break;
            }

        }
    }

    private void searchForWord() {
        System.out.println("Enter word id");
        int answer = 0;
        try {
            answer = Integer.parseInt(scanner.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid ID");
            return;
        }

        Optional<Entry> found = flashcardService.findById((long) answer);
        if (found.isEmpty()){
            System.out.println("Word not found");
            return;
        }
        System.out.println(found.get());
        System.out.println("What would you like to do?\n1. Return\n2. Modify\n3. Delete");
        int answer2 = 0;
        try {
            answer2 = Integer.parseInt(scanner.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid answer.");
        }
        switch (answer2) {
            case 1:
                break;
            case 2:
                System.out.println("Enter word in english, german and polish. Please use ; as a separator:");
                String answer3 = scanner.nextLine();
                String[] words = answer3.split(";");
                if (words.length == 3) {
                    for (String w : words) {
                        if (w.isEmpty()) {
                            System.out.println("Please enter all words");
                            return;
                        }
                    }
                } else {
                    System.out.println("Please enter all words");
                    return;
                }
                flashcardService.modifyFound(found.get().getId(), words[0].trim(), words[1].trim(), words[2].trim());
                System.out.println("Word modified successfully");
                break;
            case 3:
                flashcardService.deleteEntry(answer);
                System.out.println("Word deleted.");

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
        List<String> languages = Arrays.asList("e", "E", "p", "P", "g", "G");
        List<String> orders = Arrays.asList("a", "A", "d", "D");
        System.out.println("Enter sort criteria:\n [e] for english, [g] for german, [p] for polish, [o] for original");
        String language = scanner.nextLine();
        if (language.equals("o")) {
            displayMode.displayEntries(flashcardService.getAllEntries());
            return;
        }
        if (!languages.contains(language)){
            System.out.println("Sorry, but you entered an invalid language");
            return;
        }
        System.out.println("Enter sorting order:\n [a] for ascending, [d] for descending");
        String order = scanner.nextLine();
        if (!orders.contains(order)){
            System.out.println("Sorry, but you entered an invalid order");
            return;
        }
        if (order.isEmpty() || language.isEmpty()) {
            System.out.println("Something went wrong");
            return;
        }

            displayMode.displayEntries(flashcardService.getAllEntries(language, order));

    }

    private void addNewEntry() {
        System.out.println("Enter id, word in english, german and polish. Please use ; as a separator. \n Example: 1;dog;hund;pies");
        String e = scanner.nextLine();
        String[] words = e.split(";");
        if (words.length == 4){
            flashcardService.addNewEntry(Integer.valueOf(words[0].trim()), words[1].trim(), words[2].trim(), words[3].trim());
            System.out.printf("New entry added (%s-%s-%s)%n", displayMode.changeEntryDisplay(words[1].trim()), displayMode.changeEntryDisplay(words[2].trim()), displayMode.changeEntryDisplay(words[3].trim()));
        } else{
            System.out.println("something went wrong");
        }
    }
}
