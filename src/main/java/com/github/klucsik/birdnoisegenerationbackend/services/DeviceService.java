package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import com.github.klucsik.birdnoisegenerationbackend.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository repository;


    //Save
    public DeviceDto save(DeviceDto dto) {
        Device device = DeviceMapper.MAPPER.Dtotodevice(dto);
        return DeviceMapper.MAPPER.devicetoDto(repository.save(device));
    }

    //Read
    public DeviceDto GetById(Long id) {
        Optional<Device> device = repository.findById(id);
        if (device.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return DeviceMapper.MAPPER.devicetoDto(device.get());
    }

    public List<DeviceDto> getAll() {
        return repository.findAll().stream().map(DeviceMapper.MAPPER::devicetoDto).collect(Collectors.toList()); //I dont even wanna know what is this
    }

    public DeviceDto findByChipId(String chipId) {
        return DeviceMapper.MAPPER.devicetoDto(repository.findByChipId(chipId));
    }

    //Delete
    public void delete(Long id) {
        Optional<Device> device = repository.findById(id);
        if (device.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

}
