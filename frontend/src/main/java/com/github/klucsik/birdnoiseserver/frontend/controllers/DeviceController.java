package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DeviceConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{id}")
    public String editDeviceForm(@PathVariable Long id, Model model){

        model.addAttribute("deviceDto", connector.getById(id).getBody());
        model.addAttribute("title","Edit device");
        return "device/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DeviceDto deviceDto, Model model, RedirectAttributes attributes){
        try {
            DeviceDto savedDto = connector.save(deviceDto).getBody();
            attributes.addFlashAttribute("message", String.format("Successful save with id %d",savedDto.getId()));
            return "redirect:/device/page";
        } catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());

            if(deviceDto.getId() != null){
                return String.format("redirect:/device/%d",deviceDto.getId());
            }
            return "redirect:/device/save";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            connector.delete(id);
            attributes.addFlashAttribute("message",("Successful delete"));
            return "redirect:/";
        }catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error:", e);
            return "redirect:/";
        }
    }
}
