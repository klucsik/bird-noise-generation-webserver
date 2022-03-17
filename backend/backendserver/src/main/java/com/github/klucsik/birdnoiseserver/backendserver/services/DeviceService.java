package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceStatus;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceRepository;
import com.github.klucsik.birdnoiseserver.backendserver.validators.DeviceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository repository;
    private final DeviceValidator validator;
    private Set<Integer> autoNumSet = new HashSet<>();


    //Save
    public Device save(Device device) throws MethodArgumentNotValidException {
        validator.validate(device);
        return repository.save(device);
    }

    public Device findByChipIdOrCreateUnregistered(String chipId) throws MethodArgumentNotValidException {

        Device device = findByChipId(chipId);

        if (device == null) {
            int generatedNum = autoNumSet.size();
            device = new Device();

            if (!autoNumSet.add(generatedNum)) {
                generatedNum += 1;
            }

            device.setStatus(DeviceStatus.UNREGISTERED);
            device.setChipId(chipId);
            device.setName(
                    "Date generated: " + LocalDateTime.now() + ", " +
                            "chipId: " + chipId + ", " +
                            "GeneratedNum: " + generatedNum
            );
        }

        return save(device);
    }


    //Read
    public Device GetById(Long id) {
        Optional<Device> device = repository.findById(id);
        if (device.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return device.get();
    }

    public List<Device> getAll() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    public Device findByChipId(String chipId) {
        Device device = repository.findByChipId(chipId);
        return device;
    }

    public void setVersionOfDevice(DeviceLog log, String chipId) {
        Device device = findByChipId(chipId);
        if (!device.getVersion().equals(log.getAdditional()) & log.getMessageCode().equals("7") & device.getVersionDate() < log.getTimestamp()) {
            device.setVersion(log.getAdditional());
            device.setVersionDate(log.getTimestamp());
            log.setDevice(device);
        }
    }

    public String getFreshVersion() {
        List<Device> list = getAll();
        if (list.isEmpty()) { return null; }
        Device freshDevice = list.get(0);
        list.forEach(device -> {
            if (device.getVersionDate() > freshDevice.getVersionDate()) {
                freshDevice.setVersion(device.getVersion());
                freshDevice.setVersionDate(device.getVersionDate());
            }
        });
        if (freshDevice.getVersion() != null) {
            return freshDevice.getVersion();
        } else {
            return "No version is available";
        }

    }
    public Integer versionChecker(String version) {
        List<Device> list = getAll();
        if (list.isEmpty()) { return null; }
        List<Device> rightVersion = new ArrayList<>();
        list.forEach(device -> {
            if (device.getVersion().equals(version)) {
                rightVersion.add(device);
            }
        });
        return rightVersion.size();
    }
    //Delete
    public void delete(Long id) {
        Device device = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("There is no device with id: %d", id)));
        repository.deleteById(id);
    }

}
