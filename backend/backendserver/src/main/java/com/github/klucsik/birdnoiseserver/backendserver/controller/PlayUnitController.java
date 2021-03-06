package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendserver.services.PlayUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/playUnit")
@RequiredArgsConstructor
public class PlayUnitController extends BaseController {
    private final PlayUnitService service;

    @PostMapping("/save")
    public ResponseEntity<PlayUnitDto> save(@Valid @RequestBody PlayUnitDto dto) throws MethodArgumentNotValidException {
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayUnitDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<PlayUnitDto>> getPage() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlayUnitDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
