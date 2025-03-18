package pl.edu.pja.dziabor.tpo_03spring_data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringDataEntryRepository extends CrudRepository<Entry, Long> {

}
