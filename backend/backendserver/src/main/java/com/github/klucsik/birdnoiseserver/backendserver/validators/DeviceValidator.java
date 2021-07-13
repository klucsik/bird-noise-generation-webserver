package com.github.klucsik.birdnoisegenerationbackend.validators;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import com.github.klucsik.birdnoisegenerationbackend.repository.DeviceRepository;
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
            errors.add(new FieldError("Device", "chipId", "ChipId must be unique"));
        }

        if (repository.existsByName(device.getName())) {
            errors.add(new FieldError("Device", "name", "Name must be unique"));
        }
        baseValidator.validateAnnotations(device, errors, "Device");
    }
}