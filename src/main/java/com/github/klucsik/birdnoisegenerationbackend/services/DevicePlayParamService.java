package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.PlayParamDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DevicePlayParamMapper;
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
