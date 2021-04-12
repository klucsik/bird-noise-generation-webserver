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

    public PlayUnitDto(Integer minPause,Integer maxPause,List<TrackDto> trackList){
        this.minPause=minPause;
        this.maxPause=maxPause;
        this.trackList=trackList;
    }
}
