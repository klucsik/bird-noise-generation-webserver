package com.github.klucsik.birdnoisegenerationbackend.mappers;

import com.github.klucsik.birdnoisegenerationbackend.dto.PlayParamDto;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayParamMapper {
    PlayParamMapper MAPPER = Mappers.getMapper(PlayParamMapper.class);

    PlayParamDto playParamtoDto(PlayParam playParam);
    PlayParam dtoToPlayParam(PlayParamDto dto);
}
