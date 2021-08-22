package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "playUnit", url = "${BACKEND_URL}/playUnit")
public interface PlayUnitConnector {
    @PostMapping("/save")
    ResponseEntity<PlayUnitDto> saveTrack(PlayUnitDto dto);

    @GetMapping("/{id}")
    ResponseEntity<PlayUnitDto> getOne(@PathVariable Long id);

    @GetMapping("/page")
    ResponseEntity<List<PlayUnitDto>> getPage();

    @DeleteMapping("/{id}")
    ResponseEntity<PlayUnitDto> delete(@PathVariable Long id);
}
