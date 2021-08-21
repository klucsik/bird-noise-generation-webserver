package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.PlayParamConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/playParam")
@RequiredArgsConstructor
public class PlayParamController {
    private final PlayParamConnector controller;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<PlayParamDto> playParamDtoList = controller.getPage().getBody();
        model.addAttribute("playParamDtoList", playParamDtoList);
        return "playParam/page";
    }
}
