package com.github.klucsik.birdnoiseserver.backendserver.connectors;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "device", url = "${WEBUPDATE_URL}", fallback = WebupdateFallbak.class)
public interface WebupdateConnector {
    @GetMapping("/check")
    ResponseEntity<String> checkVersion(@RequestParam String name, @RequestParam String version);
}

@Component
class WebupdateFallbak implements WebupdateConnector{
    @Override
    public ResponseEntity<String> checkVersion(@RequestParam String name, @RequestParam String version){
        return new ResponseEntity<>("Error", HttpStatus.I_AM_A_TEAPOT);
    }
}