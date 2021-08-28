package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DeviceConnector;
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
    private final DeviceConnector deviceConnector;

    @GetMapping("/page")
    public String getAllById(Model model,@RequestParam Long id) {
        List<DevicePlayParamDto> devicePlayParamDtos = connector.getAllByDevice(id).getBody();
        model.addAttribute("devicePlayParamDtos", devicePlayParamDtos);
        model.addAttribute("deviceId", id);
        return "DevicePlayParam/page";
    }

    @GetMapping("/new")
    public String newDevicePlayParamForm(@RequestParam Long id, Model model){
        DeviceDto deviceDto = deviceConnector.getById(id).getBody();

        DevicePlayParamDto devicePlayParamDto = new DevicePlayParamDto();
        devicePlayParamDto.setDevice(deviceDto);

        model.addAttribute("devicePlayParamDto", devicePlayParamDto);
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
