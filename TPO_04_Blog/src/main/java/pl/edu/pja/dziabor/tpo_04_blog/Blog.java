package pl.edu.pja.dziabor.tpo_04_blog;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(mappedBy = "managedBlog", cascade = CascadeType.MERGE)
    private User manager;
    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Article> articles = new ArrayList<Article>();

    public Blog() {}

    public Blog(String name) {
        this.name = name;
    }
    public User getUser() {
        return manager;
    }

    public void setUser(User user) {
        this.manager = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void addArticle(Article a) {
        articles.add(a);
    }

    @Override
    public String toString() {
        String s = String.format("[%2d] %s", id, name);
        if (manager != null) {
            s += String.format("\n  Manager: %s", manager.getEmail());
        }
        return s;
    }
}
