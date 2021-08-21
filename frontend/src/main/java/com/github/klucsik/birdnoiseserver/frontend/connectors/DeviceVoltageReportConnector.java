package com.github.klucsik.birdnoiseserver.frontend.connectors;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "deviceVoltage", url = "${BACKEND_URL}/deviceVoltage")
public interface DeviceVoltageReportConnector {
    @GetMapping("/save")
    ResponseEntity<Long> saveDeviceVolt(String chipId, Float voltage);
}
