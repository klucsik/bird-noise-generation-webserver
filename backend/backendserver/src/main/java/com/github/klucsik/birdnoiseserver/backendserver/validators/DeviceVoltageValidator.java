package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceVoltage;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DeviceVoltageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeviceVoltageValidator {
    private final BaseValidator baseValidator;
    private final DeviceVoltageRepository repository;

    public void validate(DeviceVoltage deviceVoltage) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();

        baseValidator.validateAnnotations(deviceVoltage, errors, "deviceVoltage");
    }
}
