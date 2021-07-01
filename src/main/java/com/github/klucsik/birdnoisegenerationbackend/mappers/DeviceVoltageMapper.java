package com.github.klucsik.birdnoisegenerationbackend.mappers;

import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.DeviceVoltage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeviceVoltageMapper {
    DeviceVoltageMapper MAPPER = Mappers.getMapper(DeviceVoltageMapper.class);

    DeviceVoltageDto deviceVolttoDto (DeviceVoltage DeviceVolt);
    DeviceVoltage DtotodeviceVolt (DeviceVoltageDto dto);

}
