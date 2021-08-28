package com.github.klucsik.birdnoiseserver.frontend.stupidDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class FrontEndPlayParamDto {
    private Long id;
    private String name;
    private Integer vol; //volume
    private Map<Integer, Long> playUnits;

}
