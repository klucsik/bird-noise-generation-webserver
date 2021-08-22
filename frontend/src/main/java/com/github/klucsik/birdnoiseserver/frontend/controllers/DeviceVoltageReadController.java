package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DeviceVoltageReadConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/deviceVoltage")
@Controller
@RequiredArgsConstructor
public class DeviceVoltageReadController {
    private final DeviceVoltageReadConnector connector;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<DeviceVoltageDto> deviceVoltageDtoList = connector.readAll().getBody();
        model.addAttribute("deviceVoltageDtoList", deviceVoltageDtoList);
        return "deviceVoltage/page";
    }
}
