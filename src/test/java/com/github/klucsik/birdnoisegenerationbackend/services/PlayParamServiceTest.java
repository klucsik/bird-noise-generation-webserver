package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.PlayParamDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.PlayUnitDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.TrackDto;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayUnit;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class PlayParamServiceTest {
    private final TrackService trackService;
    private final PlayUnitService playUnitService;
    private final PlayParamService playParamService;
    @Test
    void save() {
        //Given
        TrackDto track1Dto = trackService.save(new TrackDto(1,"Teszt track 1",210));
        List<TrackDto> trackDtoList = new ArrayList<>();
        trackDtoList.add(track1Dto);
        PlayUnitDto playUnitDto = playUnitService.save(new PlayUnitDto(10,15,trackDtoList));
        //When
        Map<Integer,PlayUnitDto> playUnits = new HashMap<>();
        playUnits.put(1,playUnitDto);
        PlayParamDto playParamDto = new PlayParamDto(-1L,"Teszt PlayParams 1",11, playUnits);
       PlayParamDto savedPlayParamDto = playParamService.save(playParamDto);
        //Then
       assert savedPlayParamDto.getId() > 0;
       assert savedPlayParamDto.getPlayUnits().equals(playUnits);
       assert savedPlayParamDto.getName().equals("Teszt PlayParams 1");
    }
}