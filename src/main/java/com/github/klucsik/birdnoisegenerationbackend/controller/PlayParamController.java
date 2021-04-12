package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.PlayParamDto;
import com.github.klucsik.birdnoisegenerationbackend.services.PlayParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playParam")
@RequiredArgsConstructor
public class PlayParamController {
    private final PlayParamService service;

    @PostMapping("/save")
    public ResponseEntity<PlayParamDto> saveTrack(@RequestBody PlayParamDto dto){
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayParamDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id),HttpStatus.OK);
    }

    @GetMapping("/mock")
    public ResponseEntity<PlayParamDto> getMock() {
        return new ResponseEntity<>(service.getMock(),HttpStatus.OK);
    }
}
