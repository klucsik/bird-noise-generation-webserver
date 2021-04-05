package com.github.klucsik.birdnoisegenerationbackend.mappers;

import com.github.klucsik.birdnoisegenerationbackend.dto.PlayUnitDto;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayUnit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayUnitMapper {
    PlayUnitMapper MAPPER = Mappers.getMapper( PlayUnitMapper.class );

    PlayUnitDto playUnitToDto(PlayUnit playUnit);
    PlayUnit DtoToPlayUnit(PlayUnitDto dto);


}
