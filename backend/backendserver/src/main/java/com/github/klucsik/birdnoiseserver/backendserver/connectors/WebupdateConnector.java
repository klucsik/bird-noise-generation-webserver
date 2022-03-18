package com.github.klucsik.birdnoiseserver.backendserver.connectors;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "device", url = "${WEBUPDATE_URL}")
public interface WebupdateConnector {
    @GetMapping("/check")
    ResponseEntity<String> checkVersion(@RequestParam String name, @RequestParam String version);
}
