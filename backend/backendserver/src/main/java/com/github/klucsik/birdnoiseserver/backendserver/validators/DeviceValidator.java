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
        }

        if (repository.existsByName(device.getName())) {
            Device existingDevice = repository.findByChipId(device.getChipId());
            if (device.getId() != null && existingDevice.getId() != device.getId())
            errors.add(new FieldError("Device", "name", "Name must be unique"));
        }

        baseValidator.validateAnnotations(device, errors, "Device");
    }

    public void validateUpdate(Device device) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();
        Device existingDevice = repository.findByChipId(device.getChipId());

        if (existingDevice.getChipId() == device.getChipId() && existingDevice.getId() != device.getId()) {
            errors.add(new FieldError("Device", "chipId", "ChipId must be unique"));
        }

        existingDevice = repository.findByChipId(device.getChipId());

        if (existingDevice.getName() == device.getName() && existingDevice.getId() != device.getId()) {
            errors.add(new FieldError("Device", "name", "Name must be unique"));
        }

        baseValidator.validateAnnotations(device, errors, "DeviceUpdate");
    }
}
