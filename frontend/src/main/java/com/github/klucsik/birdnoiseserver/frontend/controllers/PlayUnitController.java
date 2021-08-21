package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.PlayUnitConnector;
import com.github.klucsik.birdnoiseserver.frontend.connectors.TrackConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/playUnit")
public class PlayUnitController {
    private final PlayUnitConnector connector;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<PlayUnitDto> playUnitDtoList = connector.getPage().getBody();
        model.addAttribute("PlayUnitDtoList",playUnitDtoList);
        return "playUnit/page";
    }
}
