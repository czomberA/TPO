package pl.edu.pja.dziabor.tpo_04_blog;

import jakarta.persistence.*;

@Entity
@Table()
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(nullable = true)
    private User author;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Blog blog;

    public Article() {}

    public Article(String title, User author, Blog blog) {
        this.title = title;
        this.author = author;
        this.blog = blog;
    }

    public Article(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", blog=" + blog +
                '}';
    }

    public String listString() {
        String s = String.format("[%2d] %s", id, title);
        if (author != null) {
            s += String.format("\n  Author: %s", author.getEmail());
        }
        if (blog != null) {
            s += String.format("\n  Blog: %s", blog.getName());
        }
        return s;
    }
}
