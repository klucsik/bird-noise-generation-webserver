package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DeviceConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/device")
@Controller
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceConnector connector;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<DeviceDto> deviceDtoList = connector.getPage().getBody();
        model.addAttribute("deviceDtoList", deviceDtoList);
        return "device/page";
    }
}
