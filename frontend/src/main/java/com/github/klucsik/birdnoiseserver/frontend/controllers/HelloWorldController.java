package com.github.klucsik.birdnoiseserver.frontend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required = false, defaultValue = "Uram") String name){

        return "Szia " + name;
    }

    @GetMapping("/time")
    public String time(){

        return LocalDateTime.now().toString();
    }
}
