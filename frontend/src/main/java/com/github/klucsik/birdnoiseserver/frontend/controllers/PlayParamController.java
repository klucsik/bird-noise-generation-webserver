package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.PlayParamConnector;
import com.github.klucsik.birdnoiseserver.frontend.connectors.PlayUnitConnector;
import com.github.klucsik.birdnoiseserver.frontend.stupidDtos.FrontEndPlayParamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/playParam")
@RequiredArgsConstructor
public class PlayParamController {
    private final PlayParamConnector connector;
    private final PlayUnitConnector playUnitConnector;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<PlayParamDto> playParamDtoList = connector.getPage().getBody();
        model.addAttribute("playParamDtoList", playParamDtoList);
        return "playParam/page";
    }

    @GetMapping("/new")
    public String newPlayParamForm(Model model){
        model.addAttribute("playUnitList", playUnitConnector.getPage().getBody());
        PlayParamDto playParamDto =new PlayParamDto();
        Map<Integer, PlayUnitDto> playUnits = new HashMap<>();
        for (int i = 1; i < 25; i++) {
            playUnits.put(i,new PlayUnitDto());
        }
        playParamDto.setPlayUnits(playUnits);
        model.addAttribute("playParamDto", playParamDto);
        model.addAttribute("title", "New PlayParam");
        return "playParam/save";
    }

    @GetMapping("/{id}")
    public String editPlayParamFrom(@PathVariable Long id, Model model){
        model.addAttribute("playUnitList", playUnitConnector.getPage().getBody());
        PlayParamDto playParamDto =connector.getOne(id).getBody();
        Map<Integer, PlayUnitDto> playUnits = new HashMap<>();
        for (int i = 1; i < 25; i++) {
            if(playParamDto.getPlayUnits().containsKey(i)) {
                playUnits.put(i,playParamDto.getPlayUnits().get(i));
            } else {
                playUnits.put(i, new PlayUnitDto());
            }
        }
        playParamDto.setPlayUnits(playUnits);
        model.addAttribute("playParamDto", playParamDto);
        model.addAttribute("title", "Edit title");
        return "playParam/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FrontEndPlayParamDto frontEndDto, RedirectAttributes attributes) {
        try {
            PlayParamDto mappedDto = new PlayParamDto();

            //Mapping
            mappedDto.setId(frontEndDto.getId());
            mappedDto.setName(frontEndDto.getName());
            mappedDto.setVol(frontEndDto.getVol());
            Map<Integer, PlayUnitDto> playUnits = new HashMap<>();
            frontEndDto.getPlayUnits().forEach((hour, playUnitId) -> {
                if (playUnitId != null){
                    playUnits.put(hour,playUnitConnector.getOne(playUnitId).getBody());
                }
            });
            mappedDto.setPlayUnits(playUnits);

            PlayParamDto savedDto = connector.save(mappedDto).getBody();
            attributes.addFlashAttribute("message", String.format(" successful save with id %d",savedDto.getId()));
            return "redirect:/playParam/page";
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            if(frontEndDto.getId() != null){
                return String.format("redirect:/track/%d",frontEndDto.getId());
            }
            return "redirect:/playParam/new";
        }
    }

    @GetMapping("{id}/delete")
    public String deleteTrack(@PathVariable Long id, Model model, RedirectAttributes attributes){
        try {
            connector.delete(id);
            attributes.addFlashAttribute("message",("Successful delete"));
            return "redirect:/playParam/page";
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/playParam/page";
        }
    }

}
