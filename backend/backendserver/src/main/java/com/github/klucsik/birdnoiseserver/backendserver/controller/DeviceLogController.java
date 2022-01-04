package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceLogMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.services.DeviceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deviceLog")
@RequiredArgsConstructor
public class DeviceLogController {
    private final DeviceLogService service;

    //NO FRONTEND - No frontend tichy touchy here, this is for devices.

    /* OLD SAVE NOT USED JUST LEFT IN FOR A WHILE IF THE NEW ONE IS READY DELETE THIS
    @PostMapping("/save")
    public ResponseEntity<Long> saveDeviceVolt(@RequestParam String chipId, @RequestBody DeviceLogDto dto ) throws MethodArgumentNotValidException {
        return new ResponseEntity<>(service.save(chipId, dto.getLogLevel(),dto.getMessage()), HttpStatus.OK);
    }*/

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody String data) throws MethodArgumentNotValidException {
        return new ResponseEntity<>(service.save(data), HttpStatus.OK);
    }

    @GetMapping("/page/{deviceId}")
    public ResponseEntity<List<DeviceLogDto>> pageByDeviceId(@PathVariable Long deviceId){
        return  new ResponseEntity<>(
                service.getAllByDeviceById(deviceId).stream()
                        .map(DeviceLogMapper.MAPPER::entityToDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
