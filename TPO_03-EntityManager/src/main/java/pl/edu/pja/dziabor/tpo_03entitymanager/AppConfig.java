package pl.edu.pja.dziabor.tpo_03entitymanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {
    @Bean
    Scanner scanner () {
        return new Scanner(System.in);
    }
}
