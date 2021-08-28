package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DeviceConnector;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DevicePlayParamConnector;
import com.github.klucsik.birdnoiseserver.frontend.connectors.PlayParamConnector;
import com.github.klucsik.birdnoiseserver.frontend.stupidDtos.FrontEndDevicePlayParamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/devicePlayParam")
@RequiredArgsConstructor
@Controller
public class DevicePlayParamController {
    private final DevicePlayParamConnector connector;
    private final DeviceConnector deviceConnector;
    private final PlayParamConnector playParamConnector;

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
        model.addAttribute("playParamList", playParamConnector.getPage().getBody());
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
    public String save(@ModelAttribute FrontEndDevicePlayParamDto frontEndDevicePlayParamDto, RedirectAttributes attributes){
        try {
            DevicePlayParamDto mappedDto = new DevicePlayParamDto();
            mappedDto.setStartTime(LocalDateTime.parse(frontEndDevicePlayParamDto.getStartTime()));
            mappedDto.setStopTime(LocalDateTime.parse(frontEndDevicePlayParamDto.getStopTime()));
            mappedDto.setDevice(deviceConnector.getById(frontEndDevicePlayParamDto.getDevice()).getBody());
            mappedDto.setPlayParam(playParamConnector.getOne(frontEndDevicePlayParamDto.getPlayParam()).getBody());
            DevicePlayParamDto savedDto = connector.save(mappedDto).getBody();
            attributes.addFlashAttribute("message", String.format("Successful save with id %d",savedDto.getId()));
            return String.format("redirect:/devicePlayParam/page?id=%d", savedDto.getDevice().getId());
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());

            if(frontEndDevicePlayParamDto.getId() != null){
                return String.format("redirect:/devicePlayParam/%d", frontEndDevicePlayParamDto.getId());
            }
            return String.format("redirect:/devicePlayParam/new?id=%d", frontEndDevicePlayParamDto.getDevice());
        }
    }
}
