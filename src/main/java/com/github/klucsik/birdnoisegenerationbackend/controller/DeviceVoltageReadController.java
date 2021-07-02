package com.github.klucsik.birdnoisegenerationbackend.controller;

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
    @GetMapping("/readAll") //TODO ezt én eddig pagenek hívtam, kérlek csináljuk konzisztensre
    public ResponseEntity<List<DeviceVoltageDto>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @GetMapping("/readByChipId")
    public ResponseEntity<List<DeviceVoltageDto>> readAllByChipId(@RequestParam String chipId) {
        return new ResponseEntity<>(service.readAllByChipId(chipId), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceVoltageDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
