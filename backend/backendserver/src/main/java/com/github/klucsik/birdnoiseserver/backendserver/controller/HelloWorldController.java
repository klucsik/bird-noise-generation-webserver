package com.github.klucsik.birdnoiseserver.backendserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required = false, defaultValue = "Uram") String name){ //What if the person is female? This is a serious issue and needs fixing imminently

        return "Szia " + name;
    }

    @GetMapping("/time")
    public String time(){

        return LocalDateTime.now().toString();
    }
}
