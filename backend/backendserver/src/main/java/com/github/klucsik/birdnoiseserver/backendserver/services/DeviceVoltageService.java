package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.BaseResponseDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceVoltageMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceVoltage;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceVoltageRepository;
import com.github.klucsik.birdnoiseserver.backendserver.validators.DeviceVoltageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceVoltageService {
    private final DeviceVoltageRepository repository;
    private final DeviceService deviceService;
    private final DeviceVoltageValidator validator;

    //Save because there is only one Service and to Controllers
    public Long save(String chipId, Float voltage) throws MethodArgumentNotValidException {
        DeviceVoltage deviceVoltage = new DeviceVoltage();

        deviceVoltage.setVoltage(voltage);
        validator.validate(deviceVoltage);
        Device device = deviceService.findByChipId(chipId);
        if (device == null) {
            device = deviceService.createUnregistered(chipId);
        }
        deviceVoltage.setDevice(device);
        deviceVoltage.setCreatedAt(LocalDateTime.now());


        repository.save(deviceVoltage);
        return deviceVoltage.getId();
    }


    //Read
    public List<DeviceVoltageDto> page() {
        return repository.findAll().stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }

    public List<DeviceVoltageDto> readAllByChipId(String chipId) {
        Device device = deviceService.findByChipId(chipId);

        return repository.findAllByDevice(device).stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }

    public DeviceVoltageDto getOne(Long id) {
        return DeviceVoltageMapper.MAPPER.deviceVolttoDto(repository.getOne(id));
    }


    //delete
    public BaseResponseDto delete(Long id) {
        repository.existsById(id);
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("There is no voltage report with id: %d", id));
        }
        repository.deleteById(id);
        return new BaseResponseDto(String.format("Deleted DeviceVoltage with id: %d", id));
    }

    public BaseResponseDto deleteAllByChipId(String chipId) {
        Device device = deviceService.findByChipId(chipId);
        List<DeviceVoltage> list = repository.findAllByDevice(device);
        repository.deleteAll(list);
        return new BaseResponseDto(String.format("Deleted %d DeviceVoltage", list.size()));
    }
}
