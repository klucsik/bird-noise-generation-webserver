package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.BaseResponseDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceVoltageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "deviceVoltage", url = "${BACKEND_URL}/deviceVoltage")
public interface DeviceVoltageReadConnector {
    @GetMapping("/page")
    ResponseEntity<List<DeviceVoltageDto>> readAll();

    @GetMapping("/readByChipId")
    ResponseEntity<List<DeviceVoltageDto>> readAllByChipId(String chipId);

    @GetMapping("/{id}")
    ResponseEntity<DeviceVoltageDto> getOne(Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<BaseResponseDto> delete(Long id);

    @DeleteMapping("/deleteByChipId")
    ResponseEntity<BaseResponseDto> deleteAllByChipId(String chipId);
}
