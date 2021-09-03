package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceStatus;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceRepository;
import com.github.klucsik.birdnoiseserver.backendserver.validators.DeviceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository repository;
    private final DeviceValidator validator;
    private Set<Integer> autoNumSet = new HashSet<>();


    //Save
    public DeviceDto save(DeviceDto dto) throws MethodArgumentNotValidException {
        Device device = DeviceMapper.MAPPER.Dtotodevice(dto);
        validator.validate(device);
        return DeviceMapper.MAPPER.devicetoDto(repository.save(device));
    }

    public DeviceDto createUnregistered(String chipId) throws MethodArgumentNotValidException {

        DeviceDto deviceDto = findByChipId(chipId);

        if (deviceDto == null) {
            int generatedNum = autoNumSet.size();
            deviceDto = new DeviceDto();

            if (!autoNumSet.add(generatedNum)) {
                generatedNum += 1;
            }

            deviceDto.setStatus(DeviceStatus.UNREGISTERED);
            deviceDto.setChipId(chipId);
            deviceDto.setName(
                    "Date generated: " + LocalDateTime.now() + ", " +
                            "chipId: " + chipId + ", " +
                            "GeneratedNum: " + generatedNum
            );
        }

        return save(deviceDto);
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
        return repository.findAll().stream().map(DeviceMapper.MAPPER::devicetoDto).collect(Collectors.toList());
    }

    public DeviceDto findByChipId(String chipId) {
        Device device = repository.findByChipId(chipId);
        return DeviceMapper.MAPPER.devicetoDto(device);
    }

    //Delete
    public void delete(Long id) {
        Device device = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("There is no device with id: %d", id)));
        repository.deleteById(id);
    }

}
