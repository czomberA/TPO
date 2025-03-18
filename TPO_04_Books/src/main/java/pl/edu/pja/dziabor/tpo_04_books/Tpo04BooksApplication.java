package pl.edu.pja.dziabor.tpo_04_books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Tpo04BooksApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Tpo04BooksApplication.class, args);
        AppService appService = context.getBean(AppService.class);
        //adding an authors
        Author author = new Author("TEST", "AUTHOR");
        appService.getAuthorRepository().save(author);
        Author author2 = new Author("TEST2", "AUTHOR2");
        appService.getAuthorRepository().save(author2);
        //adding a publisher
        Publisher publisher = new Publisher("TEST PUBLISHER");
        appService.getPublisherRepository().save(publisher);

        //adding a book
        List<Author> newBookAuthors = new ArrayList<>();
        newBookAuthors.add(author);
        newBookAuthors.add(author2);
        Book book = new Book("TEST BOOK", 19.00, newBookAuthors, publisher);
        appService.getBookRepository().save(book);

        System.out.println("----All entries created:----");
        appService.getBookRepository().findById(book.getId()).ifPresent(System.out::println);

    }

}
