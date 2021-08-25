package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayParamDto {
    private Long id;
    private String name;
    private Integer vol; //volume
    private Map<Integer, PlayUnitDto> playUnits; //hour-playUnit pairs
}
