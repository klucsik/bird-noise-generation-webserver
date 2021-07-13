package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DevicePlayUnitDto {
    private Integer hour;
    private Integer minT;
    private Integer maxT;
    private List<Integer> tracks;
}
