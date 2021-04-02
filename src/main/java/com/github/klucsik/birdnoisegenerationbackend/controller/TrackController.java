package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.TrackDto;
import com.github.klucsik.birdnoisegenerationbackend.services.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/track")
@RequiredArgsConstructor
public class TrackController {
    private final TrackService service;

    @PostMapping("/save")
    public ResponseEntity<TrackDto> saveTrack(@RequestBody TrackDto dto){
        return new ResponseEntity<>(service.save(dto),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id),HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<TrackDto>> getPage()  {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<TrackDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
