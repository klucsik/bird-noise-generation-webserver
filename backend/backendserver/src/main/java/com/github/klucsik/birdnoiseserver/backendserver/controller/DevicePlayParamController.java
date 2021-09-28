package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
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
            DevicePlayParamDto savedDto = DevicePlayParamMapper.MAPPER.devicePlayParamToDto(saved);
            return new ResponseEntity<>(savedDto, HttpStatus.OK); //TODO: make a mapping in the controller everywhere
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

    @GetMapping("/setToDeployable")
    public ResponseEntity<String> setToDeployable(@RequestParam Long DPPId) {
        return new ResponseEntity<>(service.setToDeployable(DPPId), HttpStatus.OK);
    }

    @GetMapping("/setToDraft")
    public ResponseEntity<String> setToDraft(@RequestParam Long DPPId) {
        return new ResponseEntity<>(service.setToDraft(DPPId), HttpStatus.OK);
    }

    @GetMapping("/setToDeleted")
    public ResponseEntity<String> setToDeleted(@RequestParam Long DPPId) {
        return new ResponseEntity<>(service.setToDeleted(DPPId), HttpStatus.OK);
    }

    @GetMapping("/findDeviceByDPPId")
    public ResponseEntity<DeviceDto> findDeviceByDPPId(@RequestParam Long DPPId) {
        return new ResponseEntity<>(DeviceMapper.MAPPER.devicetoDto(service.findDeviceByDPPId(DPPId)), HttpStatus.OK);
    }
}
