package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DevicePlayParamConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/devicePlayParam")
@RequiredArgsConstructor
@Controller
public class DevicePlayParamController {
    private final DevicePlayParamConnector connector;

    @GetMapping("/page")
    public String getAllByChipId(Model model,@RequestParam String chipId) {
        List<DevicePlayParamDto> devicePlayParamDtos = connector.getAllByDevice(chipId).getBody();
        model.addAttribute("devicePlayParamDtos", devicePlayParamDtos);
        return "DevicePlayParam/page";
    }

    @GetMapping("/new")
    public String newDevicePlayParamForm(Model model){

        model.addAttribute("devicePlayParamDto", new DevicePlayParamDto());
        model.addAttribute("title","New devicePlayParam");
        return "/devicePlayParam/save";
    }

    @GetMapping("/{id}")
    public String editDevicePlayParamForm(@PathVariable Long id, Model model){

        model.addAttribute("devicePlayParamDto", connector.getById(id).getBody()); //TODO make getOnes consistent pls
        model.addAttribute("title","Edit devicePlayParam");
        return "/devicePlayParam/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DevicePlayParamDto devicePlayParamDto, Model model, RedirectAttributes attributes){
        try {
            DevicePlayParamDto savedDto = connector.save(devicePlayParamDto).getBody();
            attributes.addFlashAttribute("message", String.format("Successful save with id %d",savedDto.getId()));
            return "redirect:/devicePlayParam/page";
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());

            if(devicePlayParamDto.getId() != null){
                return String.format("redirect:/devicePlayParam/%d", devicePlayParamDto.getId());
            }
            return "redirect:/devicePlayParam/new";
        }
    }
}
