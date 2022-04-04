package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceLogMapper;
import com.github.klucsik.birdnoiseserver.backendserver.services.DeviceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
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

    @PostMapping("/save")
    public ResponseEntity<DeviceLogDto> save(@RequestBody DeviceLogDto deviceLogDto, @RequestParam String chipId) throws MethodArgumentNotValidException {
        return new ResponseEntity<>(DeviceLogMapper.MAPPER.entityToDto(service.save(chipId, deviceLogDto)), HttpStatus.OK);
    }

    @GetMapping("/page/{deviceId}")
    public ResponseEntity<List<DeviceLogDto>> pageByDeviceId(@PathVariable Long deviceId){
        return  new ResponseEntity<>(
                service.getAllByDeviceById(deviceId).stream()
                        .map(DeviceLogMapper.MAPPER::entityToDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/errors")
    public ResponseEntity<List<DeviceLogDto>> pageByDeviceId(@RequestParam(defaultValue = "3") Integer day){
        return  new ResponseEntity<>(
                service.getAllErrorLogsLastDays(day).stream()
                        .map(DeviceLogMapper.MAPPER::entityToDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("errorNumber")
    public ResponseEntity<Integer> errorNumber(@RequestParam(defaultValue = "3") Integer day){
        return new ResponseEntity<>(service.getErrorNumber(day),HttpStatus.OK);
    }

}
