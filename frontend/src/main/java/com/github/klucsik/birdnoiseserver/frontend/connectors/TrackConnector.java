package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="track", url="${BACKEND_URL}/track")
public interface TrackConnector {

    @PostMapping("/save")
    ResponseEntity<TrackDto> saveTrack(TrackDto dto);


    @GetMapping("/{id}")
    ResponseEntity<TrackDto> getOne(@RequestParam Long id);


    @GetMapping("/page")
     ResponseEntity<List<TrackDto>> getPage();

    @DeleteMapping("/{id}")
    ResponseEntity<TrackDto> delete(@RequestParam Long id);

}
