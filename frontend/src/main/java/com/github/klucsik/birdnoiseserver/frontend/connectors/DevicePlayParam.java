package com.github.klucsik.birdnoiseserver.frontend.connectors;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "devicePlayParam", url = "${BACKEND_URL}/devicePlayParam")
public interface DevicePlayParam {

}
