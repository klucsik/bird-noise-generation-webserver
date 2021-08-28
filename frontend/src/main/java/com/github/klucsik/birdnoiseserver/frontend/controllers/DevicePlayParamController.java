package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DevicePlayParamConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
