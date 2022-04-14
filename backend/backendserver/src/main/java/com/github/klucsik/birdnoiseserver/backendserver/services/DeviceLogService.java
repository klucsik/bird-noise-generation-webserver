package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceMessages;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceLogService {
    private final DeviceLogRepository repository;
    private final DeviceService deviceService;

    public DeviceLog save(String chipId, DeviceLogDto dto) throws MethodArgumentNotValidException {
        DeviceLog log = new DeviceLog();

        log.setCreatedAt(LocalDateTime.now());
        log.setTimestamp(dto.getTimestamp());

        ZoneId zone = Calendar.getInstance().getTimeZone().toZoneId(); //Get the JVM timezone
        ZoneOffset zoneOffSet = zone.getRules().getOffset(LocalDateTime.now());
        log.setLoggedTime(LocalDateTime.ofEpochSecond(dto.getTimestamp(), 0, zoneOffSet)); //correct the logged time to the server timezone
        log.setDevice(deviceService.findByChipIdOrCreateUnregistered(chipId));
        log.setMessageCode(dto.getMessageCode());
        log.setMessage(DeviceMessages.MessageCodetoHumanReadable(dto.getMessageCode()) == null ? dto.getMessageCode().toString() : DeviceMessages.MessageCodetoHumanReadable(dto.getMessageCode()).label); //translate with enum, if available
        log.setAdditional(dto.getAdditional());
        deviceService.setVersionOfDevice(log, chipId);

        return repository.save(log);
    }

    public List<DeviceLog> getAllByDeviceById(Long id) {
        return new ArrayList<>(repository.findAllByDevice(deviceService.GetById(id)));
    }

    public List<DeviceLog> getAllErrorLogsLastDays( Integer day){
        return new ArrayList<>(repository.findAllByLoggedTimeIsAfterAndMessageCodeGreaterThanEqual(LocalDateTime.now().minusDays(day),90));
    }
    public Integer getErrorNumber(Integer day){
        return repository.countAllByLoggedTimeIsAfterAndMessageCodeGreaterThanEqual(LocalDateTime.now().minusDays(day),90);
    }

}
