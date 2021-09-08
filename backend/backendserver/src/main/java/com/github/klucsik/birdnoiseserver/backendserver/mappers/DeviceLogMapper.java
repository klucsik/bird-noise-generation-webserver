package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceLogMapper {
    DeviceLogMapper MAPPER = Mappers.getMapper(DeviceLogMapper.class);

    DeviceLog dtoToEntity (DeviceLogDto deviceLogDto);

    DeviceLogDto entityToDto (DeviceLog deviceLog);
}
