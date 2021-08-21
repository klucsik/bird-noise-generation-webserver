package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackPageSlimDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.TrackSlimMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackPageSlimService {
    private final TrackService trackService;

    public TrackPageSlimDto convert() {
        List<TrackDto> trackDtos = trackService.getAll();
        List<TrackSlimDto> trackSlimDtos = new ArrayList<>();
        TrackPageSlimDto trackPageSlimDtos = new TrackPageSlimDto();
        trackDtos.forEach(trackDto -> {
            trackSlimDtos.add(TrackSlimMapper.MAPPER.trackDtoToTrackSlimDto(trackDto));
        });
        trackPageSlimDtos.setTracklengths(trackSlimDtos);
        return trackPageSlimDtos;
    }
}
