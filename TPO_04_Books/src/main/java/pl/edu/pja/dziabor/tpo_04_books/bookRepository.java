package pl.edu.pja.dziabor.tpo_04_books;

import org.springframework.data.repository.CrudRepository;

public interface bookRepository extends CrudRepository<Book, Long> {
}
