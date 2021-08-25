package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeviceValidator {
    private final BaseValidator baseValidator;
    private final DeviceRepository repository;

    public void validate(Device device) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();

        if (repository.existsByChipId(device.getChipId())) {
            Device existingDevice = repository.findByChipId(device.getChipId());
            if (device.getId() != null && existingDevice.getId() != device.getId()) {
                errors.add(new FieldError("Device", "chipId", "ChipId must be unique"));
            }

            if (device.getId() == null) {
                errors.add(new FieldError("Device", "chipId", "ChipId must be unique!"));
            }
        }

        if (repository.existsByName(device.getName())) {
            Device existingDevice = repository.findByName(device.getName());
            if (device.getId() != null && existingDevice.getId() != device.getId()) {
                errors.add(new FieldError("Device", "name", "Name must be unique"));
            }
            if (device.getId() == null) {
                errors.add(new FieldError("Device", "name", "Name must be unique!"));
            }


        }

        baseValidator.validateAnnotations(device, errors, "Device");
    }
}
