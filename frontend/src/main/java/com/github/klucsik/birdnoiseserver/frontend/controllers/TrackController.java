package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.TrackConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
