package com.github.klucsik.birdnoisegenerationbackend.dto;

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
