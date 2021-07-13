package com.github.klucsik.birdnoiseserver.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BirdnoisegenerationfrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BirdnoisegenerationfrontendApplication.class, args);
    }

}
