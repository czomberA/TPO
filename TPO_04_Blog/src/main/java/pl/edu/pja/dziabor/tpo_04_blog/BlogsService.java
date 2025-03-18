package pl.edu.pja.dziabor.tpo_04_blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.dziabor.tpo_04_blog.repositories.ArticleRepository;
import pl.edu.pja.dziabor.tpo_04_blog.repositories.BlogRepository;
import pl.edu.pja.dziabor.tpo_04_blog.repositories.RoleRepository;
import pl.edu.pja.dziabor.tpo_04_blog.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlogsService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public BlogsService(ArticleRepository articleRepository, UserRepository userRepository, BlogRepository blogRepository, RoleRepository roleRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.roleRepository = roleRepository;
    }

    public void deleteUser(String uID) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(uID));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Article> articles = user.getArticles();
            for (Article article : articles) {
                article.setAuthor(null);
                articleRepository.save(article);
            }
            Blog managedBlog = user.getBlog();
            if (managedBlog != null) {
                managedBlog.setUser(null);
                blogRepository.save(managedBlog);
            }
            userRepository.delete(user);
        } else {
            System.out.println("User not found");
        }
    }

    public void deleteBlog(String bID) {
        Optional<Blog> blogOptional = blogRepository.findById(Long.valueOf(bID));
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            List<Article> articles = blog.getArticles();
            for (Article article : articles) {
                article.setBlog(null);
                articleRepository.save(article);
            }
            User blogManager = blog.getUser();
            if (blogManager != null) {
                blogManager.setBlog(null);
                userRepository.save(blogManager);
            }
            blogRepository.delete(blog);
        } else {
            System.out.println("No blog found");
        }
    }

    public void deleteArticle(String aID) {
        Optional<Article> articleOptional = articleRepository.findById(Long.valueOf(aID));
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            articleRepository.delete(article);
        } else {
            System.out.println("No such article");
        }
    }

    public void deleteRole(String rID) {
        Optional<Role> roleOptional = roleRepository.findById(Long.valueOf(rID));
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            List<User> usersWithRole = role.getUsers();
            for (User user : usersWithRole) {
                user.getRoles().remove(role);
                userRepository.save(user);
            }
            roleRepository.delete(role);
        } else {
            System.out.println("No role found");
        }
    }

    public void addUser(String email){
        User u = new User();
        u.setEmail(email);
        userRepository.save(u);
    }

    public void addUser(String email, String managedBlog) {
        User u = new User();
        u.setEmail(email);
        Optional<Blog> optionalBlog = blogRepository.findById(Long.valueOf(managedBlog));
        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();
            blog.setUser(u);
            userRepository.save(u);
            blogRepository.save(blog);
        } else {
            System.out.println("No blog found");
        }

    }

    public void addBlog(String name, String managerBlog) {
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(managerBlog));
        Blog b = new Blog();
        b.setName(name);
        if (optionalUser.isPresent()) {
            User blogUser = optionalUser.get();
            b.setUser(blogUser);
            blogRepository.save(b);
            blogUser.setBlog(b);
            userRepository.save(blogUser);
        } else {
            System.out.println("User not found");
        }
    }

    public void addBlog(String name) {
        Blog b = new Blog();
        b.setName(name);
        blogRepository.save(b);
    }

    public void addArticle(String title, String blogArticle, String authorArticle) {
        Article a = new Article();
        a.setTitle(title);
        if (!authorArticle.isEmpty()) {
            Optional<User> userOptional = userRepository.findById(Long.valueOf(authorArticle));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                a.setAuthor(user);
            }
            else {
                System.out.println("User not found");
            }
        }
        if (!blogArticle.isEmpty()) {
            Optional<Blog> blogOptional = blogRepository.findById(Long.valueOf(blogArticle));
            if (blogOptional.isPresent()) {
                Blog blog = blogOptional.get();
                a.setBlog(blog);
            }else {
                System.out.println("Blog not found");
            }
        }
        articleRepository.save(a);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Iterable<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Iterable<Role> getRoles() {
        return roleRepository.findAll();
    }

    public void addRole(String roleName) {
        Role r = new Role();
        r.setName(roleName);
        roleRepository.save(r);
    }

    public void assignRole(String roleID, String userID) {
        Optional<User> userO = userRepository.findById(Long.valueOf(userID));
        Optional<Role> roleO = roleRepository.findById(Long.valueOf(roleID));
        if (userO.isPresent() && roleO.isPresent()) {
            User user = userO.get();
            Role role = roleO.get();
            user.addRole(role);
            role.addUser(user);
            userRepository.save(user);
        }
    }
}
