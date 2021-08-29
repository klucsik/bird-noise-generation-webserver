package com.github.klucsik.birdnoiseserver.backendserver.controller;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.services.PlayParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playParam")
@RequiredArgsConstructor
public class PlayParamController extends BaseController {
    private final PlayParamService service;

    @PostMapping("/save")
    public ResponseEntity<PlayParamDto> save(@RequestBody PlayParamDto dto) throws MethodArgumentNotValidException {
        PlayParam saved = service.save(PlayParamMapper.MAPPER.dtoToPlayParam(dto));
        return new ResponseEntity<>(PlayParamMapper.MAPPER.playParamtoDto(saved), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayParamDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(PlayParamMapper.MAPPER.playParamtoDto(service.getOne(id)), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<PlayParamDto>> getPage() {
        return new ResponseEntity<>(PlayParamMapper.MAPPER.playParamListToDto(service.getAll()), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<PlayParamDto> deleteOne(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
