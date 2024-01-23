package com.example.mealmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MealmateApplication {
    public static void main(String[] args) {
        SpringApplication.run(MealmateApplication.class, args);
    }
}
