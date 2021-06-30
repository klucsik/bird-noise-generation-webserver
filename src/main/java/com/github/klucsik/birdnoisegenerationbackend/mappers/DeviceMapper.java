package com.github.klucsik.birdnoisegenerationbackend.mappers;

import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceDto;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface DeviceMapper {
    DeviceMapper MAPPER = Mappers.getMapper(DeviceMapper.class);

    DeviceDto devicetoDto (Device device);
    Device Dtotodevice (DeviceDto dto);

    List<DeviceDto> deviceListToDo(List<Device> deviceList);
}
