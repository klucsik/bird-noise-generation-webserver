package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.services.DevicePlayParamSlimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PlayParamSlim")
@RequiredArgsConstructor
public class DevicePlayParamSlimController {
    private final DevicePlayParamSlimService service;

    @GetMapping("/{id}")
    public ResponseEntity<DevicePlayParamSlimDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }
}
