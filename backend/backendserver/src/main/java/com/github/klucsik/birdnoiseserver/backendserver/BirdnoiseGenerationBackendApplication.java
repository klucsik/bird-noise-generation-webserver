package com.github.klucsik.birdnoiseserver.backendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BirdnoiseGenerationBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BirdnoiseGenerationBackendApplication.class, args);
    }

}
