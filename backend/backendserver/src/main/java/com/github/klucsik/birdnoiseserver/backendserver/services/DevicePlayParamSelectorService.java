package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamSlimMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DevicePlayParamRepository;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceRepository;
import com.github.klucsik.birdnoiseserver.backendserver.repository.PlayParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DevicePlayParamSelectorService {
    private final DevicePlayParamRepository repository;
    private final DeviceRepository deviceRepository;
    private final PlayParamRepository playParamRepository;

    public DevicePlayParamSlimDto selectSlimPlayParam(String chipId, Long paramVersion) {
        Device device = deviceRepository.findByChipId(chipId);
        List<DevicePlayParam> devicePlayParams = repository.getAllByDevice(device);


        devicePlayParams = devicePlayParams.stream().filter(devicePlayParam -> devicePlayParam.getStopTime().isAfter(LocalDateTime.now())).collect(Collectors.toList());

        devicePlayParams = devicePlayParams.stream().filter(devicePlayParam -> devicePlayParam.getStartTime().isBefore(LocalDateTime.now())).collect(Collectors.toList());

        switch (devicePlayParams.size()) {
            case 1:
                PlayParamDto playParamDto = PlayParamMapper.MAPPER.playParamtoDto(playParamRepository.getOne(paramVersion));
                DevicePlayParamSlimDto dto = DevicePlayParamSlimMapper.MAPPER.playParamDtotoDevice(playParamDto);
                return dto;


            case 0:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);


            default:
                return null; //TODO raise expection coze something is fucky here, lets send up the list for debugging, also it should be validated
        }

    }
}
