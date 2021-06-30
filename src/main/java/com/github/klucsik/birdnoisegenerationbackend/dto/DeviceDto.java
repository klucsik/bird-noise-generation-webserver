package com.github.klucsik.birdnoisegenerationbackend.dto;

import com.github.klucsik.birdnoisegenerationbackend.persistence.enums.DeviceStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeviceDto {
    private Long id;
    private String name;
    private String location;
    private DeviceStatus status;

    public DeviceDto(String name, String location, DeviceStatus status){
        this.name = name;
        this.location = location;
        this.status = status;
    }
}
