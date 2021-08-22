package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.services.DevicePlayParamSelectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devicePlayParamSelector")
@RequiredArgsConstructor
public class DevicePlayParamSelectorController {
private final DevicePlayParamSelectorService service;

    @GetMapping("/selectSlimPlayParam")
    public ResponseEntity<DevicePlayParamSlimDto> selectSlimPlayParam(@RequestParam String chipId,@RequestParam Long paramVersion) {
        return new ResponseEntity<>(service.selectSlimPlayParam(chipId, paramVersion), HttpStatus.OK);
    }
}
