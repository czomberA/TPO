package pl.edu.pja.dziabor.tpo_04_blog.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.dziabor.tpo_04_blog.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
