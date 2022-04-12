package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DevicePlayParamRepository;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceRepository;
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
    private final DevicePlayParamSlimService devicePlayParamSlimService;

    public DevicePlayParamSlimDto selectSlimPlayParam(String chipId, Long paramVersion) {
        Device device = deviceRepository.findByChipId(chipId);
        if (device == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No device found for chipId: %s", chipId));
        }
        List<DevicePlayParam> playParamsForDevice = repository.getAllByDevice(device);


        playParamsForDevice = playParamsForDevice.stream().filter(
                devicePlayParam -> devicePlayParam.getStopTime().isAfter(
                        LocalDateTime.now())).collect(Collectors.toList());

        playParamsForDevice = playParamsForDevice.stream().filter(
                devicePlayParam -> devicePlayParam.getStartTime().isBefore(
                        LocalDateTime.now())).collect(Collectors.toList());

        switch (playParamsForDevice.size()) {
            case 1:
                if (paramVersion == playParamsForDevice.get(0).getPlayParam().getId()) {
                    return null;
                } else {
                    return devicePlayParamSlimService.getOne(playParamsForDevice.get(0).getPlayParam().getId());
                }

            case 0:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No playparam found for chipId: %s", chipId));

            default:
                throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, String.format("Conflicting playParams: %s", playParamsForDevice));

        }
    }
}
