package pl.edu.pja.dziabor.tpo_03spring_data;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entry {

    @Id
    private long id;
    private String english;
    private String german;
    private String polish;

    Entry(int id, String english, String german, String polish) {
        this.id = id;
        this.english = english;
        this.german = german;
        this.polish = polish;
    }

    public Entry() {

    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public void setPolish(String polish) {
        this.polish = polish;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    @Override
    public String toString() {
        return
                "english='" + english + '\'' +
                ", polish='" + polish + '\'' +
                ", german='" + german + '\'' +
                '}';
    }
}

