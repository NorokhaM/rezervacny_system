package com.hackaton.rezervacny_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RezervacnySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RezervacnySystemApplication.class, args);
    }

}
