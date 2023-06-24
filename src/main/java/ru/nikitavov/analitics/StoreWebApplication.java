package ru.nikitavov.analitics;

import ru.nikitavov.analitics.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({AppProperties.class})
@SpringBootApplication
public class StoreWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreWebApplication.class, args);
    }

}
