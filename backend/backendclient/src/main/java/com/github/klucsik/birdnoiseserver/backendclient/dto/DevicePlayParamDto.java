package com.github.klucsik.birdnoiseserver.backendclient.dto;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DPPStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DevicePlayParamDto {
    private Long id;

    private DeviceDto device;

    private PlayParamDto playParam;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime stopTime;

    private DPPStatus status;
}
