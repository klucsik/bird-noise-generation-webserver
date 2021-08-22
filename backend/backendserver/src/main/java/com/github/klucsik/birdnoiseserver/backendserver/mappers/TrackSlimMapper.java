package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackSlimDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackSlimMapper {
    TrackSlimMapper MAPPER = Mappers.getMapper(TrackSlimMapper.class);

    TrackSlimDto trackDtoToTrackSlimDto(TrackDto trackDto);
}
