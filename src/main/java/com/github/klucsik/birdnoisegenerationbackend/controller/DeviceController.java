package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceDto;
import com.github.klucsik.birdnoisegenerationbackend.services.DeviceService;
import com.github.klucsik.birdnoisegenerationbackend.validators.BaseValidator;
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
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.GetById(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<DeviceDto>> getPage() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
