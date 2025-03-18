package pl.edu.pja.dziabor.tpo_04_books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    private publisherRepository publisherRepository;
    private bookRepository bookRepository;
    private authorRepository authorRepository;

    @Autowired
    public AppService(pl.edu.pja.dziabor.tpo_04_books.publisherRepository publisherRepository, pl.edu.pja.dziabor.tpo_04_books.bookRepository bookRepository, pl.edu.pja.dziabor.tpo_04_books.authorRepository authorRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public pl.edu.pja.dziabor.tpo_04_books.publisherRepository getPublisherRepository() {
        return publisherRepository;
    }

    public pl.edu.pja.dziabor.tpo_04_books.bookRepository getBookRepository() {
        return bookRepository;
    }

    public pl.edu.pja.dziabor.tpo_04_books.authorRepository getAuthorRepository() {
        return authorRepository;
    }
}
