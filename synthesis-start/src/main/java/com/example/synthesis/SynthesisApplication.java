package com.example.synthesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.example.synthesis"}
)
public class SynthesisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SynthesisApplication.class, args);
    }

}
