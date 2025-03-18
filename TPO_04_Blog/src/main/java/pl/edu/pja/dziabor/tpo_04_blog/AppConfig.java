package pl.edu.pja.dziabor.tpo_04_blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class AppConfig {
    @Bean
    Scanner scanner () {
        return new Scanner(System.in);
    }
    @Bean
    Pattern validEmail () {
        return Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }
}

