package pl.edu.pja.dziabor.tpo_04_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tpo04BlogApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Tpo04BlogApplication.class, args);
        BlogsController controller = context.getBean(BlogsController.class);
        controller.run();
    }

}
