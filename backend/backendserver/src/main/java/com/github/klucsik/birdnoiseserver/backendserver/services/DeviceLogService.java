package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceMessages;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceLogService {
    private final DeviceLogRepository repository;
    private final DeviceService deviceService;

    public DeviceLog save(String chipId, DeviceLogDto dto) throws MethodArgumentNotValidException {
        DeviceLog log = new DeviceLog();
        log.setDevice(deviceService.findByChipIdOrCreateUnregistered(chipId));
        log.setCreatedAt(LocalDateTime.now());
        log.setTimestamp(dto.getTimestamp());
        log.setLoggedTime(LocalDateTime.ofEpochSecond(dto.getTimestamp(), 0, ZoneOffset.of("+01:00")));

        log.setMessageCode(dto.getMessageCode());
        log.setMessage(DeviceMessages.MessageCodetoHumanReadable(dto.getMessageCode()) == null ? dto.getMessageCode() : DeviceMessages.MessageCodetoHumanReadable(dto.getMessageCode()).label  ); //translate with enum, if available
        log.setAdditional(dto.getAdditional());

        return repository.save(log);
    }

    public List<DeviceLog> getAllByDeviceById(Long id) {
        return repository.findAllByDevice(deviceService.GetById(id));
    }

}
