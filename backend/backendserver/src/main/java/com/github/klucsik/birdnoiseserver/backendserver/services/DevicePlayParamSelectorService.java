package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.enums.DPPStatus;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        List<DevicePlayParam> playParamsForDevice = repository.findByDeviceAndStatusNotAndStatusNot(device, DPPStatus.DELETED, DPPStatus.DRAFT);


        playParamsForDevice = playParamsForDevice.stream().filter(
                devicePlayParam -> devicePlayParam.getStopTime().isAfter(
                        LocalDateTime.now())).collect(Collectors.toList());

        playParamsForDevice = playParamsForDevice.stream().filter(
                devicePlayParam -> devicePlayParam.getStartTime().isBefore(
                        LocalDateTime.now())).collect(Collectors.toList());

        switch (playParamsForDevice.size()) {
            case 1:
                if (paramVersion == playParamsForDevice.get(0).getPlayParam().getId()) {
                    //current playPAram is on the device
                    if (playParamsForDevice.get(0).getStatus() == DPPStatus.DEPLOYING) {
                        //successfully deployed change status
                        playParamsForDevice.get(0).setStatus(DPPStatus.DEPLOYED);
                        return null; //because its the same on the device and on the server
                    }
                    else {
                        playParamsForDevice.get(0).setStatus(DPPStatus.DEPLOYED); //Because the playParam on the device is good
                        return null;                                              //TODO: but send a warn! log
                    }
                }
                else {
                    PlayParam oldPlayParam = playParamRepository.getOne(paramVersion);
                    DevicePlayParam oldDPP = repository.findByPlayParam(oldPlayParam);
                    oldDPP.setStatus(DPPStatus.UNDEPLOYING);

                    PlayParam newPlayParam = playParamsForDevice.get(0).getPlayParam();
                    DevicePlayParam newDPP = repository.findByPlayParam(newPlayParam);
                    newDPP.setStatus(DPPStatus.DEPLOYING);
                    return DevicePlayParamSlimMapper.MAPPER.playParamDtotoDevice(PlayParamMapper.MAPPER.playParamtoDto(newPlayParam));
                }

            case 0:
                //TODO: Log, Rohan calls for aid, there is no playPAram
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            default:
                //never ever
                throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, String.format("Conficting playParams: %s", playParamsForDevice));
        }
    }
}
