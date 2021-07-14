package com.github.klucsik.birdnoiseserver.frontend.connectors;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="track", url="${BACKEND_URL}/track")
public interface TrackConnector {
    @GetMapping("/page")
     ResponseEntity<List<TrackDto>> getPage() ;

}
