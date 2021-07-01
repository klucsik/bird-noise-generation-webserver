package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceVoltageDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceMapper;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceVoltageMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.DeviceVoltage;
import com.github.klucsik.birdnoisegenerationbackend.repository.DeviceVoltageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

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

        return DeviceVoltageMapper.MAPPER.deviceVolttoDto(repository.save(deviceVoltage));
    }

    //Read


}
