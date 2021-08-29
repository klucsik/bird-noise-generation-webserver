package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController extends BaseController {
    private final DeviceService service;

    @PostMapping("/save")
    public ResponseEntity<DeviceDto> saveDevice(@RequestBody DeviceDto dto) throws MethodArgumentNotValidException {
        Device saved = service.save(DeviceMapper.MAPPER.Dtotodevice(dto));
        return new ResponseEntity<>(DeviceMapper.MAPPER.devicetoDto(saved), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(DeviceMapper.MAPPER.devicetoDto(service.GetById(id)), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<DeviceDto>> getPage() {
        return new ResponseEntity<>(DeviceMapper.MAPPER.deviceListToDo(service.getAll()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
