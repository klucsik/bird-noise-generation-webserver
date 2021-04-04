package com.github.klucsik.birdnoisegenerationbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required = false, defaultValue = "Uram") String name){

        return "Szia " + name;
    }
}
