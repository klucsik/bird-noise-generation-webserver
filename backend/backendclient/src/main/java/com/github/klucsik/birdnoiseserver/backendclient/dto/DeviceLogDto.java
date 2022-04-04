package com.github.klucsik.birdnoiseserver.backendclient.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DeviceLogDto {
    private Long id;

    private DeviceDto device;

    private Integer messageCode; //sent from device
    private String message; //decoded at BE
    private String additional;

    private LocalDateTime createdAt;
    private Long timestamp; //epoch from device
    private LocalDateTime loggedTime; //time from the epoch timestamp
}
