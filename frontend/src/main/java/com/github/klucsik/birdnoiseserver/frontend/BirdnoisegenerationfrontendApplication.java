package com.github.klucsik.birdnoiseserver.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients
public class BirdnoisegenerationfrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BirdnoisegenerationfrontendApplication.class, args);
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Budapest"));
    }

}
