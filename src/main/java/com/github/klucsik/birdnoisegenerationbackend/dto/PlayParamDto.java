package com.github.klucsik.birdnoisegenerationbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class PlayParamDto {
    private Long id;
    private String name;
    private Map<Integer,PlayUnitDto> playUnits;


}
