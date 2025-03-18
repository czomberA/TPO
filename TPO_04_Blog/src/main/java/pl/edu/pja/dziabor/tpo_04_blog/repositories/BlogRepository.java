package pl.edu.pja.dziabor.tpo_04_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.dziabor.tpo_04_blog.Blog;

public interface BlogRepository extends CrudRepository<Blog, Long> {
}
