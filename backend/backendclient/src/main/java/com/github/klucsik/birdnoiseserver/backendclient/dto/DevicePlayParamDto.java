package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DevicePlayParamDto {
    private Long id;

    private DeviceDto device;

    private PlayParamDto playParam;

    private LocalDateTime startTime;
    private LocalDateTime stopTime;
}
