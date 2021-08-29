package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.services.DevicePlayParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devicePlayParam")
@RequiredArgsConstructor
public class DevicePlayParamController extends BaseController {
    private final DevicePlayParamService service;

    @PostMapping("/save")
    public ResponseEntity<DevicePlayParamDto> save(@RequestBody DevicePlayParamDto devicePlayParamDto)
            throws MethodArgumentNotValidException {
        DevicePlayParam saved = service.save(DevicePlayParamMapper.MAPPER.dtoToDevicePLayParam(devicePlayParamDto));
        return new ResponseEntity<>(DevicePlayParamMapper.MAPPER.devicePlayParamToDto(saved), HttpStatus.OK); //TODO: make a mapping in the controller everywhere
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevicePlayParamDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(DevicePlayParamMapper.MAPPER.devicePlayParamToDto(service.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/getAllByDevice")
    public ResponseEntity<List<DevicePlayParamDto>> getAllByDevice(@RequestParam Long id) {
        return new ResponseEntity<>(DevicePlayParamMapper.MAPPER.devicePlayParamListToDto(service.getAllByDevice(id)), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<DevicePlayParamDto>> page() {
        return new ResponseEntity<>(DevicePlayParamMapper.MAPPER.devicePlayParamListToDto(service.getPage()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DevicePlayParamDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
