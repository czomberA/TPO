package pl.edu.pja.dziabor.tpo10.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.dziabor.tpo10.Models.Link;

import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, String> {
    Optional<Link> findByTargetUrl(String url);
}
