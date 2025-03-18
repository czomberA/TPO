package pl.edu.pja.dziabor.tpo_04_books;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double price;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors;
    @ManyToOne()
    @JoinColumn
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, double price, List<Author> authors, Publisher publisher) {
        this.title = title;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return "Book:" +
                "\n   id: " + id +
                "\n   title: " + title +
                "\n   price: " + price +
                "\n   authors: " + authors +
                "\n   publisher: " + publisher;
    }
}
