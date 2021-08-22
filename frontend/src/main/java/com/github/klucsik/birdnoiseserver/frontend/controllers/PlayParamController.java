package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.PlayParamConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/playParam")
@RequiredArgsConstructor
public class PlayParamController {
    private final PlayParamConnector connector;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<PlayParamDto> playParamDtoList = connector.getPage().getBody();
        model.addAttribute("playParamDtoList", playParamDtoList);
        return "playParam/page";
    }

    @GetMapping("/new")
    public String newPlayParamForm(Model model){

        model.addAttribute("playParamDto", new PlayParamDto());
        model.addAttribute("title", "New PlayParam");
        return "playParam/new";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute PlayParamDto playParamDto,Model model, RedirectAttributes attributes) {
        try {
            PlayParamDto savedDto = connector.saveTrack(playParamDto).getBody();
            attributes.addFlashAttribute("message", String.format(" successful save with id %d",savedDto.getId()));
            return "redirect:/playParam/page";
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/playParam/new";
        }
    }
}
