package com.github.klucsik.birdnoiseserver.backendclient.dto;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeviceDto {
    private Long id;
    private String chipId;
    private String name;
    private String location;
    private DeviceStatus status;
}
