package com.github.klucsik.birdnoiseserver.backendserver.mappers;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeviceMapper {
    DeviceMapper MAPPER = Mappers.getMapper(DeviceMapper.class);

    DeviceDto devicetoDto(Device device);

    Device Dtotodevice(DeviceDto dto);

    List<DeviceDto> deviceListToDo(List<Device> deviceList);
}
