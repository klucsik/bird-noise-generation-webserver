package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DevicePlayParamDto {
    private Long paramVersion;
    private Integer vol; //volume
    private List<DevicePlayUnitDto> playParams; //hour-playUnit pairs
}
