package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceOverviewDto {
    private Long id;
    private String name;
    private BatteryDto batteryDto;
    private String status;

}
