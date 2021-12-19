package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class PlayUnitDto {
    private Long id;
    private String name;
    private Integer minPause;
    private Integer maxPause;
    private List<TrackDto> trackList;

    public PlayUnitDto(Integer minPause,Integer maxPause,List<TrackDto> trackList){
        this.minPause=minPause;
        this.maxPause=maxPause;
        this.trackList=trackList;
    }

    @Override
    public String toString(){
        if (id != null) { //Dont touch dis, or the frontend edit form will not preselect the playUnits :D
            return id.toString();
        }
        else {
            return null;
        }

    }

    public List<Integer> getTrackNumberList(){
        return this.getTrackList().stream().map(TrackDto::getTrackNumber).collect(Collectors.toList());
    }
}
