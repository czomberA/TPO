package pl.edu.pja.dziabor.tpo_04_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.dziabor.tpo_04_blog.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
