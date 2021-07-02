package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceMapper;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceVoltageMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.DeviceVoltage;
import com.github.klucsik.birdnoisegenerationbackend.repository.DeviceVoltageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceVoltageService {
    private final DeviceVoltageRepository repository;
    private final DeviceService deviceService;

    //Save because there is only one Service and to Controllers
    public DeviceVoltageDto save(String chipId, Float voltage) {
        DeviceVoltage deviceVoltage = new DeviceVoltage();

        deviceVoltage.setDevice(DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipId(chipId)));
        deviceVoltage.setVoltage(voltage);
        deviceVoltage.setCreatedAt(LocalDate.now());
        deviceVoltage.setCreatedTime(LocalTime.now());

        return DeviceVoltageMapper.MAPPER.deviceVolttoDto(repository.save(deviceVoltage));
    }

    //Read
    public List<DeviceVoltageDto> readAll() {
        return repository.findAll().stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }

    public List<DeviceVoltageDto> readAllByChipId(String chipId) {
        Device device = DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipId(chipId));

        return repository.findAllByDevice(device).stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }
}
