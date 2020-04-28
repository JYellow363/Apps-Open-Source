package com.example.demopost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemopostApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemopostApplication.class, args);
    }

}
