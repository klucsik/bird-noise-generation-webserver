package com.github.klucsik.birdnoiseserver.frontend.controllers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.frontend.connectors.PlayUnitConnector;
import com.github.klucsik.birdnoiseserver.frontend.connectors.TrackConnector;
import com.github.klucsik.birdnoiseserver.frontend.stupidDtos.StupidPlayUnitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/playUnit")
public class PlayUnitController {
    private final PlayUnitConnector connector;
    private final TrackConnector trackConnector;

    @GetMapping("/page")
    public String getPage(Model model) {
        List<PlayUnitDto> playUnitDtoList = connector.getPage().getBody();
        model.addAttribute("PlayUnitDtoList", playUnitDtoList);
        return "playUnit/page";
    }

    @GetMapping("/new")
    public String newPlayUnitForm(Model model) {
        model.addAttribute("trackList", trackConnector.getPage().getBody());
        model.addAttribute("playUnitDto", new PlayUnitDto());
        model.addAttribute("title", "New PlayUnit");
        return "playUnit/save";
    }

    @GetMapping("/{id}")
    public String editPlayUnitForm(@PathVariable Long id, Model model) {
        PlayUnitDto playUnitDto = connector.getOne(id).getBody();
        model.addAttribute("playUnitDto", playUnitDto);
        model.addAttribute("title", "Edit playUnit");
        return "/playUnit/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute StupidPlayUnitDto stupidPlayUnitDto, Model model, RedirectAttributes attributes) {
        try {
            PlayUnitDto mappedPlayUnitDto = new PlayUnitDto(); //TODO: make this somewhat less ugly
            mappedPlayUnitDto.setId(stupidPlayUnitDto.getId());
            mappedPlayUnitDto.setMinPause(stupidPlayUnitDto.getMinPause());
            mappedPlayUnitDto.setMaxPause(stupidPlayUnitDto.getMaxPause());
            List<TrackDto> trackList = new ArrayList<>();
            stupidPlayUnitDto.getTrackList().forEach(trackId ->
                    trackList.add(trackConnector.getOne(trackId).getBody())
            );
            mappedPlayUnitDto.setTrackList(trackList);
            PlayUnitDto savedDto = connector.saveTrack(mappedPlayUnitDto).getBody();
            attributes.addFlashAttribute("message", String.format(" successful save whit id %d", savedDto.getId()));
            return "redirect:/playUnit/page";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "Error:" + e.getMessage());

            if (stupidPlayUnitDto.getId() != null) {
                return String.format("redirect:/playUnit/%d", stupidPlayUnitDto.getId());
            }
            return "redirect:/playUnit/save";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        try {
            connector.delete(id);
            attributes.addFlashAttribute("message", ("Successful delete"));
            return "redirect:/playUnit/page";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "error:" + e.getMessage());
            return "redirect:/playUnit/page";
        }
    }
}
