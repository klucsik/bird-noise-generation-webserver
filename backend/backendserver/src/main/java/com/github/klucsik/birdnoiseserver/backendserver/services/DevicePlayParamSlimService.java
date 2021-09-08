package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamSlimMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DevicePlayParamSlimService {
    private final PlayParamService playParamService;

    public DevicePlayParamSlimDto getOne(Long id) {
        PlayParamDto playParamDto = PlayParamMapper.MAPPER.playParamtoDto(playParamService.getOne(id));
        return DevicePlayParamSlimMapper.MAPPER.playParamDtotoDevice(playParamDto);
    }
}
