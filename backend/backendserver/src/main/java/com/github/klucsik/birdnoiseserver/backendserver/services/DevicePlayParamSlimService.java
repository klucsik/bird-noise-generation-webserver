package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamSlimMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DevicePlayParamRepository;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevicePlayParamSlimService {
    private final PlayParamService playParamService;
    private final DevicePlayParamRepository repository;
    private final DeviceRepository deviceRepository;

    public DevicePlayParamSlimDto getOne(Long id) {
        PlayParamDto playParamDto = PlayParamMapper.MAPPER.playParamtoDto(playParamService.getOne(id));
        Map<Integer, PlayUnitDto> utcCorectedPlayUnits = new HashMap<>();
        playParamDto.getPlayUnits().forEach(
                (hour, playUnitDto) -> {
                    int utcHour = calcUtcHour(hour);
                    utcCorectedPlayUnits.put(utcHour, playUnitDto);
                }
        );
        playParamDto.setPlayUnits(utcCorectedPlayUnits);
        return DevicePlayParamSlimMapper.MAPPER.playParamDtotoDevice(playParamDto);
    }

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
                    return getOne(playParamsForDevice.get(0).getPlayParam().getId());
                }

            case 0:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No playparam found for chipId: %s", chipId));

            default:
                throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, String.format("Conflicting playParams: %s", playParamsForDevice));

        }
    }

    public int calcUtcHour(Integer hour) {
        ZoneId zone = Calendar.getInstance().getTimeZone().toZoneId(); //Get the JVM timezone
        ZoneOffset zoneOffSet = zone.getRules().getOffset(LocalDateTime.now());
        int UTCHour = hour - zoneOffSet.getTotalSeconds() / 3600; //Hour - UTC difference in hours (TotalSecond/3600)
        if (UTCHour == 0) {
            UTCHour = 24;
        }
        if (UTCHour < 0) {
            UTCHour = 24 + UTCHour;
        }
        if (UTCHour > 24) {
            UTCHour = UTCHour - 24;
        }
        return UTCHour;
    }

}