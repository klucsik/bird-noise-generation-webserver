package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.BaseResponseDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceMapper;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceVoltageMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.DeviceVoltage;
import com.github.klucsik.birdnoisegenerationbackend.repository.DeviceVoltageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceVoltageService {
    private final DeviceVoltageRepository repository;
    private final DeviceService deviceService;

    //Save because there is only one Service and to Controllers
    public Long save(String chipId, Float voltage) {
        DeviceVoltage deviceVoltage = new DeviceVoltage();

        Device device =DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipId(chipId));
        if (device == null){
            device = DeviceMapper.MAPPER.Dtotodevice(deviceService.createUnregistered(chipId));
        }
        deviceVoltage.setDevice(device);
        deviceVoltage.setVoltage(voltage);
        deviceVoltage.setCreatedAt(LocalDateTime.now());
        repository.save(deviceVoltage);
        return deviceVoltage.getId();
    }


    //Read
    public List<DeviceVoltageDto> page() {
        return repository.findAll().stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }

    public List<DeviceVoltageDto> readAllByChipId(String chipId) {
        Device device = DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipId(chipId));

        return repository.findAllByDevice(device).stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }

    public DeviceVoltageDto getOne(Long id) {
        return DeviceVoltageMapper.MAPPER.deviceVolttoDto(repository.getOne(id));
    }


    //delete
    public BaseResponseDto delete(Long id) {
        repository.existsById(id);
        if (!repository.existsById(id)) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("There is no voltage report with id: %d", id));
        }
        repository.deleteById(id);
        return new BaseResponseDto(String.format("Deleted DeviceVoltage with id: %d", id));
    }

    public BaseResponseDto deleteAllByChipId(String chipId) {
        Device device = DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipId(chipId));
        List<DeviceVoltage> list = repository.findAllByDevice(device);
        repository.deleteAll(list);
        return new BaseResponseDto(String.format("Deleted %d DeviceVoltage", list.size()));
    }
}
