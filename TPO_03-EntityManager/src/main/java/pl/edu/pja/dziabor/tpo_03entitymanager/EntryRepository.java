package pl.edu.pja.dziabor.tpo_03entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class EntryRepository {
    private final EntityManager entityManager;

    public EntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addEntry(Entry entry) {
        entityManager.persist(entry);
    }


    public boolean isDatabasePopulated() {
        String query = "SELECT COUNT(e) FROM Entry e";
        Long count = entityManager.createQuery(query, Long.class).getSingleResult();
        return count > 0;
    }

    public Optional<Entry> findById (Long id) {
        return Optional.ofNullable(entityManager.find(Entry.class, id));
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @Transactional
    public void updateEntry(long id, String english, String german, String polish) {
        Entry dbEntry;
        try{
            dbEntry =  findById(id).get();
        } catch (NoSuchElementException e){
            System.out.println("Entry not found");
            return;
        }
        dbEntry.setEnglish(english);
        dbEntry.setGerman(german);
        dbEntry.setPolish(polish);
    }

    public List<Entry> getEntries(String language, String order) {
        String lang = "";
        String ord = "";
        if (language.equalsIgnoreCase("e")){
            lang = "english";
        } else if (language.equalsIgnoreCase("g")) {
            lang = "german";
        } else if (language.equalsIgnoreCase("p")) {
            lang = "polish";
        }

        if (order.equalsIgnoreCase("a")){
            ord = "asc";
        } else if (order.equalsIgnoreCase("d")) {
            ord = "desc";
        }

        String query = "from Entry e ORDER BY e." + lang + " " + ord;
        return entityManager.createQuery(query, Entry.class).getResultList();


    }

    public List<Entry> getEntries() {
        return entityManager.createQuery("from Entry", Entry.class).getResultList();
    }

}
