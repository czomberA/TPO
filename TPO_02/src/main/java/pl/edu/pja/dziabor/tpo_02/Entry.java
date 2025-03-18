package pl.edu.pja.dziabor.tpo_02;

public class Entry {
    private String english;
    private String polish;
    private String german;

    Entry (String english, String german, String polish) {
        this.english = english;
        this.german = german;
        this.polish = polish;
    }

    public String getEnglish() {
        return english;
    }

    public String getPolish() {
        return polish;
    }

    public String getGerman() {
        return german;
    }
}
