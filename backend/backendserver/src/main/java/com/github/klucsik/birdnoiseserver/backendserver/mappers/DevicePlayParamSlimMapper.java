package com.github.klucsik.birdnoiseserver.backendserver.mappers;


import com.github.klucsik.birdnoiseserver.backendclient.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface DevicePlayParamSlimMapper {
    DevicePlayParamSlimMapper MAPPER = Mappers.getMapper(DevicePlayParamSlimMapper.class);

    @Mapping(source = "id", target = "paramVersion")
    @Mapping(source = "playUnits", target = "playParams")
    DevicePlayParamSlimDto playParamDtotoDevice(PlayParamDto playParamDto);

    //do this mapping by hand becouse we change from map to list, and need to bring in the hours.
    default List<DevicePlayUnitDto> playUnitDtotoDevice(Map<Integer, PlayUnitDto> playUnits) {
        List<DevicePlayUnitDto> devicePlayUnitDtos = new ArrayList<>();
        playUnits.forEach((integer, playUnitDto) -> {
            DevicePlayUnitDto devicePlayUnitDto = new DevicePlayUnitDto();
            devicePlayUnitDto.setHour(integer);
            devicePlayUnitDto.setMaxT(playUnitDto.getMaxPause());
            devicePlayUnitDto.setMinT(playUnitDto.getMinPause());
            devicePlayUnitDto.setTracks(playUnitDto.getTrackList().stream().map(TrackDto::getTrackNumber).collect(Collectors.toList()));
            devicePlayUnitDtos.add(devicePlayUnitDto);
        });

        return devicePlayUnitDtos;
    }


}
