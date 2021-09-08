package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "deviceLog", url = "${BACKEND_URL}/deviceLog")
public interface DeviceLogConnector {
    @GetMapping("/page/{deviceId}")
    public ResponseEntity<List<DeviceLogDto>> pageByDeviceId(@PathVariable Long deviceId);
}
