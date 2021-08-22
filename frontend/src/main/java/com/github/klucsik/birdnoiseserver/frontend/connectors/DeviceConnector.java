package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "device", url = "${BACKEND_URL}/device")
public interface DeviceConnector {
    @PostMapping("/save")
    ResponseEntity<DeviceDto> save(DeviceDto dto);

    @GetMapping("/{id}")
    ResponseEntity<DeviceDto> getById(@PathVariable Long id);

    @GetMapping("/page")
    ResponseEntity<List<DeviceDto>> getPage();

    @DeleteMapping("/{id}")
    ResponseEntity<DeviceDto> delete(@PathVariable Long id);

}
