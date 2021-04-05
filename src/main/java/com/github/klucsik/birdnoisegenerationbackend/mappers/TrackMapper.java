package com.github.klucsik.birdnoisegenerationbackend.mappers;

import com.github.klucsik.birdnoisegenerationbackend.dto.TrackDto;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A mapper class, which will map dtos to corresponding entities and vice versa, see https://mapstruct.org/
 */
@Mapper
public interface TrackMapper {
    TrackMapper MAPPER = Mappers.getMapper( TrackMapper.class );

    TrackDto trackToDto(Track track);
    Track DtoToTrack(TrackDto dto);

    List<TrackDto> trackListToDtoList(List<Track> trackList);

}
