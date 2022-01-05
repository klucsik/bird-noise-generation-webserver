package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDto;
import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceLogContentTypes;
import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceLogMessageTypes;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceLogService {
    private final DeviceLogRepository repository;
    private final DeviceService deviceService;

    public DeviceLog save(String chipId, DeviceLogDto dto) throws MethodArgumentNotValidException {
        //TODO: check if the data valid or not

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DeviceLog log = new DeviceLog();
        log.setDevice(deviceService.findByChipIdOrCreateUnregistered(chipId));
        log.setCreatedAt(LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter));
        log.setTimestamp(dto.getTimestamp());
        log.setLoggedTime(LocalDateTime.ofEpochSecond(dto.getTimestamp(), 0, ZoneOffset.of("+01:00"))); //I've done it! Levi

        log.setContentTypeCode(dto.getContentTypeCode());
        log.setContentType(DeviceLogContentTypes.valueOfNumber(dto.getContentTypeCode()) == null ? dto.getContentTypeCode() : DeviceLogContentTypes.valueOfNumber(dto.getContentTypeCode()).label  ); //translate with enum, if available
        log.setMessageCode(dto.getMessageCode());
        log.setMessage(DeviceLogMessageTypes.valueOfNumber(dto.getMessageCode()) == null ? dto.getMessageCode() : DeviceLogMessageTypes.valueOfNumber(dto.getMessageCode()).label);

        return repository.save(log);
    }

    public List<DeviceLog> getAllByDeviceById(Long id) {
        return repository.findAllByDevice(deviceService.GetById(id));
    }

}
