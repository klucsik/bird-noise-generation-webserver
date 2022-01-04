package com.github.klucsik.birdnoiseserver.backendclient.dto;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceLogContentTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeviceLogDto {
    private Long id;

    private DeviceDto device;

    private DeviceLogContentTypes contentCode;

    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private String loggedTime;
}
