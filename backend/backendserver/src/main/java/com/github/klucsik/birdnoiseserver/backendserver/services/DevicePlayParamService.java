package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamSlimMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DevicePlayParamRepository;
import com.github.klucsik.birdnoiseserver.backendserver.repository.PlayParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevicePlayParamService {
    private final PlayParamService playParamService;
    private final DeviceService deviceService;
    private final DevicePlayParamRepository repository;

    public DevicePlayParamDto save(DevicePlayParamDto devicePlayParamDto) {
        DevicePlayParam devicePlayParam = DevicePlayParamMapper.MAPPER.dtoToDevicePLayParam(devicePlayParamDto);

        devicePlayParam.setDevice(
                DeviceMapper.MAPPER.Dtotodevice(
                        deviceService.GetById(
                                devicePlayParam.getDevice().getId())));

        devicePlayParam.setPlayParam(
                PlayParamMapper.MAPPER.dtoToPlayParam(
                        playParamService.getOne(
                                devicePlayParam.getPlayParam().getId())));

        return DevicePlayParamMapper.MAPPER.devicePlayParamToDto(repository.save(devicePlayParam));
    }

    public DevicePlayParamDto getById(Long id) {
        DevicePlayParam devicePlayParam = repository.getOne(id);
        return DevicePlayParamMapper.MAPPER.devicePlayParamToDto(devicePlayParam);
    }

    public List<DevicePlayParamDto> getPage() {
        return repository.findAll().stream().map(DevicePlayParamMapper.MAPPER::devicePlayParamToDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
