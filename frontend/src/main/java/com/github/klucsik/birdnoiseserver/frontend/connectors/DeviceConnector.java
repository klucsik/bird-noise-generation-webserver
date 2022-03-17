package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "device", url = "${BACKEND_URL}/device")
public interface DeviceConnector {
    @PostMapping("/save")
    ResponseEntity<DeviceDto> save(DeviceDto dto);

    @GetMapping("/{id}")
    ResponseEntity<DeviceDto> getById(@PathVariable Long id);

    @GetMapping("/page")
    ResponseEntity<List<DeviceDto>> getPage();

    @GetMapping("/freshVersion")
    ResponseEntity<String> getFreshVersion();

    @GetMapping("/versionChecker")
    ResponseEntity<Integer> versionChecker(@RequestParam String version);

    @DeleteMapping("/{id}")
    ResponseEntity<DeviceDto> delete(@PathVariable Long id);

}
