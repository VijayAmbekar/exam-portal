package tech.surfer.examserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfig {

    @Bean("random")
    public Random random() {
        return new Random();
    }
}
