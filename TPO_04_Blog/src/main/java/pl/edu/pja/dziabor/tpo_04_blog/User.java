package pl.edu.pja.dziabor.tpo_04_blog;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "managedBlog", unique = true)
    private Blog managedBlog;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE}, orphanRemoval = false)
    private List<Article> articles = new ArrayList<Article>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private List<Role> roles = new ArrayList<Role>();

    public User() {}

    public User(String email) {
        this.email = email;
    }
    public Blog getBlog() {
        return managedBlog;
    }

    public void setBlog(Blog blog) {
        this.managedBlog = blog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }

    public void addRole(Role r){
        roles.add(r);
    }
    @Override
    public String toString() {
        String s = String.format("[%2d] %s", id, email);
        if (managedBlog != null) {
            s += String.format("\n  Managed blog: %s", managedBlog.getName());
        }
        if (!roles.isEmpty()){
            s += String.format("\n  Roles: " + roles);
        }
        return s;
    }
}
