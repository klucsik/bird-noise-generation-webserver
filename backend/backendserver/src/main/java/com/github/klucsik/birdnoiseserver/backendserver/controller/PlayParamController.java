package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.services.PlayParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playParam")
@RequiredArgsConstructor
public class PlayParamController {
    private final PlayParamService service;

    @PostMapping("/save")
    public ResponseEntity<PlayParamDto> saveTrack(@RequestBody PlayParamDto dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayParamDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<PlayParamDto>> getPage() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<PlayParamDto> deleteOne(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/mock")
    public ResponseEntity<PlayParamDto> getMock() throws MethodArgumentNotValidException {
        return new ResponseEntity<>(service.getMock(), HttpStatus.OK);
    }
}
