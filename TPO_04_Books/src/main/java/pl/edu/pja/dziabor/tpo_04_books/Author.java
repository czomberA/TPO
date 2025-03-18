package pl.edu.pja.dziabor.tpo_04_books;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToMany
    private List<Book> books;

    public Author() {}
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return
                "\n       id: " + id +
                "\n       firstName: " + firstName +
                "\n       lastName: " + lastName ;
    }
}


