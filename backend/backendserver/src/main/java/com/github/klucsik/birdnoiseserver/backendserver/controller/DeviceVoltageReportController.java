package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendserver.services.DeviceVoltageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deviceVoltage")
@RequiredArgsConstructor
public class DeviceVoltageReportController extends BaseController {
    private final DeviceVoltageService service;

    @GetMapping("/save")
    public ResponseEntity<Long> saveDeviceVolt(@RequestParam String chipId, @RequestParam Float voltage) throws MethodArgumentNotValidException {
        return new ResponseEntity<>(service.save(chipId, voltage), HttpStatus.OK);
    }

}