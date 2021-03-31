package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.TrackDto;
import com.github.klucsik.birdnoisegenerationbackend.services.TrackService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/track")
@RequiredArgsConstructor
public class TrackController {
    private final TrackService service;

    @PostMapping("/save")
    public ResponseEntity<TrackDto> saveTrack(@RequestBody TrackDto dto){
        return new ResponseEntity<>(service.save(dto),HttpStatus.OK);
    }

    @GetMapping("/getone")
    public ResponseEntity<TrackDto> getOne(@RequestParam Long id) throws NotFoundException {
        return new ResponseEntity<>(service.getOne(id),HttpStatus.OK);
    }
}
