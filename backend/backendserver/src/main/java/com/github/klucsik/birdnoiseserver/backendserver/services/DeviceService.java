package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceStatus;
import com.github.klucsik.birdnoiseserver.backendserver.connectors.WebupdateConnector;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceRepository;
import com.github.klucsik.birdnoiseserver.backendserver.validators.DeviceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository repository;
    private final DeviceValidator validator;
    private final WebupdateConnector webupdateConnector;
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
        return repository.findByChipId(chipId);
    }

    public void setVersionOfDevice(DeviceLog log, String chipId) {
        Device device = findByChipId(chipId);
        if (device.getVersionDate() == null && log.getMessageCode().equals("7") || log.getMessageCode().equals("7") && device.getVersionDate() < log.getTimestamp()) {
            device.setVersion(log.getAdditional());
            device.setVersionDate(log.getTimestamp());
            log.setDevice(device);
        }
    }

    public String getFreshVersion() {
        String update_url = webupdateConnector.checkVersion("bird_noise", "invalid").getBody();
        String[] segments = update_url.split("/");
        return segments[segments.length-1].replace(".bin","");

    }
    public Integer versionChecker(String version) {
        List<Device> list = getAll();
        if (list.isEmpty()) { return 0; }
        List<Device> rightVersion = new ArrayList<>();
        list.forEach(device -> {
            if (device.getVersionDate() != null && device.getVersion().equals(version)) {
                rightVersion.add(device);
            }
        });
        return rightVersion.size();
    }
    //Delete
    public void delete(Long id) {
        repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("There is no device with id: %d", id)));
        repository.deleteById(id);
    }

}
