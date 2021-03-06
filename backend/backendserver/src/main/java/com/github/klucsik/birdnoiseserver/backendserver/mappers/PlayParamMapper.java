package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayParamMapper {
    PlayParamMapper MAPPER = Mappers.getMapper(PlayParamMapper.class);

    PlayParamDto playParamtoDto(PlayParam playParam);

    PlayParam dtoToPlayParam(PlayParamDto dto);

    List<PlayParamDto> playParamListToDto(List<PlayParam> playParams);
}
