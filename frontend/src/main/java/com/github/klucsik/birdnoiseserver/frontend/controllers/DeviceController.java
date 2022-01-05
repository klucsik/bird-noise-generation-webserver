package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DeviceConnector;
import com.github.klucsik.birdnoiseserver.frontend.connectors.DeviceLogConnector;
import com.github.klucsik.birdnoiseserver.frontend.stupidDtos.FrontEndDeviceLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/device")
@Controller
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceConnector connector;
    private final DeviceLogConnector logConnector;

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

    @GetMapping("{id}/log")
    public String logs(@PathVariable Long id, Model model){
        List<DeviceLogDto> dto = logConnector.pageByDeviceId(id).getBody();
        List<FrontEndDeviceLogDto> stupidDtoList = new ArrayList<>();

        dto.forEach(deviceLogDto -> {
            FrontEndDeviceLogDto stupidDto = new FrontEndDeviceLogDto();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            stupidDto.setDevice(deviceLogDto.getDevice());
            stupidDto.setContentType(deviceLogDto.getContentType());
            stupidDto.setContentTypeCode(deviceLogDto.getContentTypeCode());
            stupidDto.setId(deviceLogDto.getId());
            stupidDto.setMessageCode(deviceLogDto.getMessageCode());
            stupidDto.setMessage(deviceLogDto.getMessage());
            stupidDto.setTimestamp(deviceLogDto.getTimestamp());

            stupidDto.setLoggedTime(deviceLogDto.getLoggedTime().format(formatter));
            stupidDto.setCreatedAt(deviceLogDto.getCreatedAt().format(formatter));

            stupidDtoList.add(stupidDto);
        });

        model.addAttribute("logs", stupidDtoList);
        model.addAttribute("title", String.format("Logs for device %s" ,connector.getById(id).getBody().getName()));
        return "device/logPage";
    }
}
