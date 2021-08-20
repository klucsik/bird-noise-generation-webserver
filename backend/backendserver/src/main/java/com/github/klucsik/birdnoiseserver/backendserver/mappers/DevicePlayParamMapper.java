package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DevicePlayParamMapper {
    DevicePlayParamMapper MAPPER = Mappers.getMapper(DevicePlayParamMapper.class);

    DevicePlayParam dtoToDevicePLayParam(DevicePlayParamDto devicePlayParamDto);
    DevicePlayParamDto devicePlayParamToDto(DevicePlayParam devicePlayParam);


}
