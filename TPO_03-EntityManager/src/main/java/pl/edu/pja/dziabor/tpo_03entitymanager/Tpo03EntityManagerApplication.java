package pl.edu.pja.dziabor.tpo_03entitymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tpo03EntityManagerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Tpo03EntityManagerApplication.class, args);
        FlashcardsController controller = context.getBean(FlashcardsController.class);
        controller.run();
    }

}
