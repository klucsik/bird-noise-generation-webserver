package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamSlimDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamSlimMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DevicePlayParamSlimService {
    private final PlayParamService playParamService;

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

    public int calcUtcHour(Integer hour) {
        Calendar calendar = Calendar.getInstance();
        ZoneId zone = calendar.getTimeZone().toZoneId(); //Get the JVM timezone | question Why is this needed?Levi
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