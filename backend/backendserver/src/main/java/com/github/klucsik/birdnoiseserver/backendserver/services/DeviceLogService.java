package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceLogDtoRaw;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeviceLogService {
    private final DeviceLogRepository repository;
    private final DeviceService deviceService;

    public DeviceLog save(String chipId, DeviceLogDtoRaw data) throws MethodArgumentNotValidException { //TODO JSON_BE JÖN
        //TODO: check if the data valid or not

        DeviceLog log = new DeviceLog();

        log.setLoggedTime(data.getTimestamp());
        log.setContentCode(data.getContentCode()); //TODO: say nice message whit enum
        log.setMessage(data.getMessage()); //TODO: say nice message whit enum
        log.setDevice(deviceService.findByChipId(chipId));
        log.setCreatedAt(LocalDateTime.now());



        return repository.save(log);
    }


    /* OLD SAVE NOT USED JUST LEFT IN FOR A WHILE IF THE NEW ONE IS READY DELETE THIS
    public Long save(String chipId, String loglevel, String message) throws MethodArgumentNotValidException {
        DeviceLog deviceLog = new DeviceLog();

        deviceLog.setLogLevel(loglevel);
        deviceLog.setMessage(message);
        deviceLog.setDevice(deviceService.findByChipIdOrCreateUnregistered(chipId));
        deviceLog.setCreatedAt(LocalDateTime.now());

        return repository.save(deviceLog).getId();
    }*/

    public List<DeviceLog> getAllByDeviceById(Long id) {
        return repository.findAllByDevice(deviceService.GetById(id));
    }

}
