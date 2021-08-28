package com.github.klucsik.birdnoiseserver.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping("/")
    public String getPage(Model model) {
        return "redirect:/device/page";
    }
}
