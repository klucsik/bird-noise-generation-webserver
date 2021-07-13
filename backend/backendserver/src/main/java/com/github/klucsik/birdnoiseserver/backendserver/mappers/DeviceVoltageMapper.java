package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceVoltage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface DeviceVoltageMapper {
    DeviceVoltageMapper MAPPER = Mappers.getMapper(DeviceVoltageMapper.class);

    DeviceVoltageDto deviceVolttoDto(DeviceVoltage DeviceVolt);

    DeviceVoltage DtotodeviceVolt(DeviceVoltageDto dto);

}
