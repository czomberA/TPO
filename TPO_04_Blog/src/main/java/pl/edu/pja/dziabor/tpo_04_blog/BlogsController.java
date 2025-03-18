package pl.edu.pja.dziabor.tpo_04_blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class BlogsController {
    private final Scanner scanner;
    private final BlogsService blogsService;
    public final Pattern validEmail;

    @Autowired
    public BlogsController(Scanner scanner, BlogsService blogsService, Pattern validEmail) {
        this.scanner = scanner;
        this.blogsService = blogsService;
        this.validEmail = validEmail;
    }

    public void run() {

        boolean exit = false;
        System.out.println("Please enter a number from 1 to 3 to select an option.");
        while (!exit) {
            System.out.println("~~MENU~~\n1. Add new\n2. Display all\n3. Delete \nSelect option: ");
            String menu = scanner.nextLine();
            switch (menu) {
                case "1":
                    addNew();
                    break;
                case "2":
                    displayAll();
                    break;
                case "3":
                    deleteEntry();
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 3.");
                    break;
            }

        }
    }

    private void deleteEntry() {
        System.out.println("What would you like to delete?\n1. User\n2. Blog\n3. Article\n4. Role");
        String e = scanner.nextLine();
        switch (e) {
            case "1":
                System.out.println("Enter user ID:");
                String uID = scanner.nextLine();
                try{
                blogsService.deleteUser(uID); }
                catch (Exception del1)
                {
                    System.out.println(del1);
                }
                break;
            case "2":
                System.out.println("Enter blog ID:");
                String bID = scanner.nextLine();
                try {
                    blogsService.deleteBlog(bID);
                } catch (Exception del2){
                    System.out.println(del2);
                }
                break;
            case "3":
                System.out.println("Enter article ID:");
                String aID = scanner.nextLine();
                try {
                    blogsService.deleteArticle(aID);
                } catch (Exception del3){
                    System.out.println(del3);
                }
                break;
            case "4":
                System.out.println("Enter role ID:");
                String rID = scanner.nextLine();
                try {
                    blogsService.deleteRole(rID);
                } catch (Exception del4) {
                    System.out.println(del4);
                }
                break;
        }
    }

   private void displayAll() {
        System.out.println("What would you like to display?\n1. Users\n2. Blogs\n3. Articles\n4. Roles");
        String e = scanner.nextLine();
        switch (e) {
            case "1":
                System.out.println("ALL USERS:");
                Iterable<User> users = blogsService.getUsers();
                for (User u: users){
                    System.out.println(u.toString());
                }
                break;
            case "2":
                System.out.println("ALL BLOGS:");
                Iterable<Blog> blogs = blogsService.getBlogs();
                for (Blog b: blogs){
                    System.out.println(b.toString());
                }
                break;
            case "3":
                System.out.println("ALL ARTICLES:");
                Iterable<Article> articles = blogsService.getArticles();
                for (Article a: articles){
                    System.out.println(a.toString());
                }
                break;
            case "4":
                System.out.println("ALL ROLES");
                Iterable<Role> roles = blogsService.getRoles();
                for (Role r: roles){
                    System.out.println(r.toString());
                }
                break;
        }
    }

    private void addNew() {
        System.out.println("What would you like to add?\n1. User\n2. Blog\n3. Article\n4. Role\n5. Assign role");
        String e = scanner.nextLine();
        switch (e) {
            case "1":
                System.out.println("Enter email");
                String email = scanner.nextLine();
                Matcher matcher = validEmail.matcher(email);
                if (!matcher.matches()){
                    System.out.println("Enter valid email");
                    break;
                }
                System.out.println("Do they manage a blog? Y/N");
                String manages = scanner.nextLine();
                if (manages.equals("Y") || manages.equals("y")) {
                    System.out.println("Enter blog ID");
                    String managedBlog = scanner.nextLine();
                    try {
                        blogsService.addUser(email, managedBlog);
                    } catch (Exception add1_1){
                        System.out.println(add1_1);
                    }

                } else if (manages.equals("N") || manages.equals("n")) {
                    try {
                        blogsService.addUser(email);
                    } catch (Exception add1_2){
                        System.out.println(add1_2);
                    }
                } else {
                    System.out.println("Please enter a valid option (Y/N).");
                }
                break;
            case "2":
                System.out.println("Enter name");
                String name = scanner.nextLine();
                System.out.println("Is this blog managed by someone? Y/N");
                String manager = scanner.nextLine();
                if (manager.equals("Y") || manager.equals("y")) {
                    System.out.println("Enter user ID");
                    String managerBlog = scanner.nextLine();
                    try {
                        blogsService.addBlog(name, managerBlog);
                    }catch (Exception add2_1){
                        System.out.println(add2_1);
                    }
                } else if (manager.equals("N") || manager.equals("n")) {
                    try {
                        blogsService.addBlog(name);
                    } catch (Exception add2_2){
                        System.out.println(add2_2);
                    }
                } else {
                    System.out.println("Please enter a valid option (Y/N).");
                }
                break;
            case "3":
                System.out.println("Enter title");
                String title = scanner.nextLine();
                String authorArticle = "";
                String blogArticle = "";
                System.out.println("Would you like to set an author? Y/N");
                String isAuthor = scanner.nextLine();
                if (isAuthor.equals("Y") || isAuthor.equals("y")) {
                    System.out.println("Enter user ID");
                    authorArticle = scanner.nextLine();
                } else if (!isAuthor.equals("N") || !isAuthor.equals("n")) {
                    System.out.println("Please enter a valid option (Y/N).");
                }
                System.out.println("Would you like to set a blog? Y/N");
                String isBlog = scanner.nextLine();
                if (isBlog.equals("Y") || isBlog.equals("y")) {
                    System.out.println("Enter blog ID");
                   blogArticle = scanner.nextLine();
                } else if (!isBlog.equals("N") || !isBlog.equals("n")) {
                    System.out.println("Please enter a valid option (Y/N).");
                }
                try {
                    blogsService.addArticle(title, blogArticle, authorArticle);
                } catch (Exception add3){
                    System.out.println(add3);
                }
                break;
            case "4":
                System.out.println("Enter name");
                String roleName = scanner.nextLine();
                try {
                    blogsService.addRole(roleName);
                } catch (Exception add4){
                    System.out.println(add4);
                }
                break;
            case "5":
                System.out.println("Enter role id");
                String roleID = scanner.nextLine();
                System.out.println("Enter user id");
                String userID = scanner.nextLine();
                try{
                    blogsService.assignRole(roleID, userID);
                } catch (Exception add5){
                    System.out.println(add5);
                }


        }

    }

}
