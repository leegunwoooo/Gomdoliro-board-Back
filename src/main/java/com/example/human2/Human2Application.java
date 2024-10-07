package com.example.human2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Human2Application {

    public static void main(String[] args) {
        SpringApplication.run(Human2Application.class, args);
    }

}
