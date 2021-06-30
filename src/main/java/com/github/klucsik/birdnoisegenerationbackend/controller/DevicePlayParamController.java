package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoisegenerationbackend.services.DevicePlayParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devicePlayParam")
@RequiredArgsConstructor
public class DevicePlayParamController {
    private final DevicePlayParamService service;

    @GetMapping("/{id}")
    public ResponseEntity<DevicePlayParamDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }


}
