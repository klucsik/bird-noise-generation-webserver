package com.github.klucsik.birdnoisegenerationbackend.services;

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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceVoltageService {
    private final DeviceVoltageRepository repository;
    private final DeviceService deviceService;

    //Save because there is only one Service and two Controllers
    public DeviceVoltageDto save(String chipId, Float voltage) {
        DeviceVoltage deviceVoltage = new DeviceVoltage();

        //TODO ha nincs még ilyen device, hozzunk létre egyet unregistered státusszal. (legyen a DeviceServiceben egy method hogy createUnregistered, mert ezt több helyről is meg kell majd hívni)

        deviceVoltage.setDevice(DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipId(chipId)));
        deviceVoltage.setVoltage(voltage);
        deviceVoltage.setCreatedAt(LocalDate.now());
        deviceVoltage.setCreatedTime(LocalTime.now());

        return DeviceVoltageMapper.MAPPER.deviceVolttoDto(repository.save(deviceVoltage));
    }

    //TODO egy olyan method ami {id} path varaible alapján visszaad egy recordot (getOne)
    //Read
    public List<DeviceVoltageDto> readAll() {
        return repository.findAll().stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }

    public List<DeviceVoltageDto> readAllByChipId(String chipId) {
        Device device = DeviceMapper.MAPPER.Dtotodevice(deviceService.findByChipId(chipId));

        return repository.findAllByDevice(device).stream().map(DeviceVoltageMapper.MAPPER::deviceVolttoDto).collect(Collectors.toList());
    }

    //delete
    public void delete(Long id) {
        Optional<DeviceVoltage> deviceVoltage = repository.findById(id);
        if (deviceVoltage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); //TODO: Az összes ilyenhez  kéne írni  szép hibaüznetet mint a palyunit delete methodjánál.
        }
        repository.deleteById(id);
    }

    //TODO deleteAllByChipId()
}
