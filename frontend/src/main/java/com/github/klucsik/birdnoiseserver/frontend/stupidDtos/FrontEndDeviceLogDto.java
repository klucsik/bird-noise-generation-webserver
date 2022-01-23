package com.github.klucsik.birdnoiseserver.frontend.stupidDtos;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FrontEndDeviceLogDto {
    private Long id;

    private DeviceDto device;

    private String contentTypeCode; //sent from device
    private String contentType; //decoded at BE
    private String messageCode; //sent from device
    private String message; //decoded at BE

    private String createdAt;
    private Long timestamp; //epoch from device
    private String loggedTime; //time from the epoch timestamp
}
