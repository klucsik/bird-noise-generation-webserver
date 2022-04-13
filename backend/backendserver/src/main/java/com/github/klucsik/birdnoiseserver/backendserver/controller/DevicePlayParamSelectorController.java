package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.services.DevicePlayParamSlimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devicePlayParamSelector")
@RequiredArgsConstructor
public class DevicePlayParamSelectorController {
    private final DevicePlayParamSlimService service;

    @GetMapping("/selectSlimPlayParam")
    public ResponseEntity<DevicePlayParamSlimDto> selectSlimPlayParam(@RequestParam String chipId, @RequestParam Long paramVersion) {
        //TODO add deployedTIme (and undeployed too somewhere)
        return new ResponseEntity<>(service.selectSlimPlayParam(chipId, paramVersion), HttpStatus.OK);
    }
}
