package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.DeviceMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import com.github.klucsik.birdnoisegenerationbackend.repository.DeviceRepository;
import com.github.klucsik.birdnoisegenerationbackend.validators.DeviceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository repository;
    private final DeviceValidator validator;


    //Save
    public DeviceDto save(DeviceDto dto) throws MethodArgumentNotValidException {
        Device device = DeviceMapper.MAPPER.Dtotodevice(dto);
        validator.validate(device);
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

    //Delete
    public void delete(Long id) {
        Optional<Device> device = repository.findById(id);
        if (device.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
