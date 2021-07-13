package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BatteryDto {
    private Long id;
    private String name;
    private Double voltage;
    private String colorcode;
}
