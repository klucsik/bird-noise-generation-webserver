package com.github.klucsik.birdnoisegenerationbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PlayUnitDto {
    private Long id;
    private Integer minPause;
    private Integer maxPause;
    private List<TrackDto> trackList;
}
