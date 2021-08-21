package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackPageSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.services.TrackPageSlimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/TrackPageSlim")
public class TrackPageSlimController {
    private final TrackPageSlimService service;

    @GetMapping("/page")
    public ResponseEntity<TrackPageSlimDto> convert() {
        return new ResponseEntity(service.convert(), HttpStatus.OK);
    }
}
