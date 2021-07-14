package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayUnit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayUnitMapper {
    PlayUnitMapper MAPPER = Mappers.getMapper(PlayUnitMapper.class);

    PlayUnitDto playUnitToDto(PlayUnit playUnit);

    PlayUnit DtoToPlayUnit(PlayUnitDto dto);


}
