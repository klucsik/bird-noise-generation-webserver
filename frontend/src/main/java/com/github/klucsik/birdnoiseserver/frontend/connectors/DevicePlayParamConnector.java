package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "devicePlayParam", url = "${BACKEND_URL}/devicePlayParam")
public interface DevicePlayParamConnector {
    @PostMapping("/save")
    ResponseEntity<DevicePlayParamDto> save(DevicePlayParamDto devicePlayParamDto);

    @GetMapping("/{id}")
    ResponseEntity<DevicePlayParamDto> getById(@PathVariable Long id);

    @GetMapping("/getAllByDevice")
    ResponseEntity<List<DevicePlayParamDto>> getAllByDevice(@RequestParam Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<DevicePlayParamDto> delete(@PathVariable Long id);
}
