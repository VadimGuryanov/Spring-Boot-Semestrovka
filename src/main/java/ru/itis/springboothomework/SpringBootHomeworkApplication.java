package ru.itis.springboothomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itis.springboothomework.utils.HelperError;

@SpringBootApplication
public class SpringBootHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHomeworkApplication.class, args);
    }

    @Bean
    public HelperError beanHelperRepeatRequest() {
        return new HelperError();
    }

}
