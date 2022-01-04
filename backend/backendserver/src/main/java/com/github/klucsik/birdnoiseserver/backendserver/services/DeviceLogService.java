package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceLogService {
    private final DeviceLogRepository repository;
    private final DeviceService deviceService;

    public Long save(String data) throws MethodArgumentNotValidException {
        //TODO: chack if the data valid or not

        List<String> cutText = Arrays.stream(data.split("\\=", 4)).collect(Collectors.toList()); //Separating the 3 types

        String timestamp = Arrays.stream(cutText.get(1).split("\\,", 3)).collect(Collectors.toList()).get(0); //cleaning the timestamp
        String contentCode  = Arrays.stream(cutText.get(2).split("\\,", 3)).collect(Collectors.toList()).get(0); //cleaning the contentcode
        String message = cutText.get(3);

        return null;
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
