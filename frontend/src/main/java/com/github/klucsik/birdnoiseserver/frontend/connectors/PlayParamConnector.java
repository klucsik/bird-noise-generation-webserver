package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "playParam", url = "${BACKEND_URL}/playParam")
public interface PlayParamConnector {
    @PostMapping("/save")
    ResponseEntity<PlayParamDto> saveTrack(PlayParamDto dto);

    @GetMapping("/{id}")
    ResponseEntity<PlayParamDto> getOne(Long id);

    @GetMapping("/page")
    ResponseEntity<List<PlayParamDto>> getPage();

    @DeleteMapping("/{id}")
    ResponseEntity<PlayParamDto> deleteOne(Long id);
}
