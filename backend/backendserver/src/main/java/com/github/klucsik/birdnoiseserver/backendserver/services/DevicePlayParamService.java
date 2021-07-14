package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DevicePlayParamService {
    private final PlayParamService playParamService;

    public DevicePlayParamDto getOne(Long id) {
        PlayParamDto playParamDto = playParamService.getOne(id);
        return DevicePlayParamMapper.MAPPER.playParamDtotoDevice(playParamDto);
    }
}
