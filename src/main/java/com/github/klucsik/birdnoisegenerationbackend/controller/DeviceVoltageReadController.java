package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.BaseResponseDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceVoltageMapper;
import com.github.klucsik.birdnoisegenerationbackend.services.DeviceVoltageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceVoltage")
@RequiredArgsConstructor
public class DeviceVoltageReadController {
    private final DeviceVoltageService service;

    //read
    @GetMapping("/page") 
    public ResponseEntity<List<DeviceVoltageDto>> readAll() {
        return new ResponseEntity<>(service.page(), HttpStatus.OK);
    }

    @GetMapping("/readByChipId")
    public ResponseEntity<List<DeviceVoltageDto>> readAllByChipId(@RequestParam String chipId) {
        return new ResponseEntity<>(service.readAllByChipId(chipId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceVoltageDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByChipId")
    public ResponseEntity<BaseResponseDto> deleteAllByChipId(@RequestParam String chipId) {
        return new ResponseEntity<>(service.deleteAllByChipId(chipId), HttpStatus.OK);
    }
}
