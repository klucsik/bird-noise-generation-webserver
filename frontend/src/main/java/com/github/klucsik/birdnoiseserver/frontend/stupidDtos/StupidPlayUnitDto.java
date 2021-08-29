package com.github.klucsik.birdnoiseserver.frontend.stupidDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StupidPlayUnitDto {
    private Long id;
    private String name;
    private Integer minPause;
    private Integer maxPause;
    private List<Long> trackList; //stupid thymeleaf
}
