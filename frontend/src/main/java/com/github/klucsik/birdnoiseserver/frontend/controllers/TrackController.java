package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.TrackConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/track")
@Controller
@RequiredArgsConstructor
public class TrackController {
    private final TrackConnector connector;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<TrackDto> trackDtoList = connector.getPage().getBody();
        model.addAttribute("trackDtoList",trackDtoList);
        return "track/page";
    }

    @GetMapping("/new")
    public String newTrackForm(Model model){

        model.addAttribute("trackDto", new TrackDto());
        model.addAttribute("title","New track");
        return "/track/save";
    }

    @GetMapping("/{id}")
    public String editTrackForm(@PathVariable Long id, Model model){

        model.addAttribute("trackDto", connector.getOne(id).getBody());
        model.addAttribute("title","Edit track");
        return "/track/save";
    }

    @PostMapping("/save")
    public String saveTrack(@ModelAttribute TrackDto trackDto, Model model, RedirectAttributes attributes){
        try {
            TrackDto savedDto = connector.saveTrack(trackDto).getBody();
            attributes.addFlashAttribute("message", String.format("Successful save with id %d",savedDto.getId()));
            return "redirect:/track/page";
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());

            if(trackDto.getId() != null){
                return String.format("redirect:/track/%d", trackDto.getId());
            }
            //TODO prefill with the failed data
            return "redirect:/track/new";
        }
    }

    @GetMapping("{id}/delete")
    public String deleteTrack(@PathVariable Long id, Model model, RedirectAttributes attributes){
        try {
            connector.delete(id);
            attributes.addFlashAttribute("message",("Successful delete"));
            return "redirect:/track/page";
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/track/page";
        }
    }
}
