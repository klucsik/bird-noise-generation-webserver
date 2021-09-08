package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceLogService {
    private final DeviceLogRepository repository;
    private final DeviceService deviceService;

    public Long save(String chipId, String loglevel, String message) throws MethodArgumentNotValidException {
        DeviceLog deviceLog = new DeviceLog();

        deviceLog.setLogLevel(loglevel);
        deviceLog.setMessage(message);
        deviceLog.setDevice(DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipIdOrCreateUnregistered(chipId)));
        deviceLog.setCreatedAt(LocalDateTime.now());

        return repository.save(deviceLog).getId();
    }

    public List<DeviceLog> getAllByDeviceById(Long id){
       return repository.findAllByDevice(DeviceMapper.MAPPER.Dtotodevice(deviceService.GetById(id)));
    }

}
