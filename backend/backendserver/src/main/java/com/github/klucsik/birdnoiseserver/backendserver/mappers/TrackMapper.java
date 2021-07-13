package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * A mapper class, which will map dtos to corresponding entities and vice versa, see https://mapstruct.org/
 */
@Mapper
public interface TrackMapper {
    TrackMapper MAPPER = Mappers.getMapper(TrackMapper.class);

    TrackDto trackToDto(Track track);

    Track DtoToTrack(TrackDto dto);

    List<TrackDto> trackListToDtoList(List<Track> trackList);

}
