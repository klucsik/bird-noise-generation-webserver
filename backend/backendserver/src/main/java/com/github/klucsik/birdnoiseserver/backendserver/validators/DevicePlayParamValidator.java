package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DevicePlayParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DevicePlayParamValidator {
    private final BaseValidator baseValidator;
    private final DevicePlayParamRepository repository;

    public void validate(DevicePlayParam devicePlayParam) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();
        List<DevicePlayParam> allSavedDPP = repository.getAllByDevice(devicePlayParam.getDevice());

        allSavedDPP.forEach(DevicePlayParam -> {
            boolean smth = isOverlaping(
                    devicePlayParam.getStartTime(),
                    devicePlayParam.getStopTime(),
                    DevicePlayParam.getStartTime(),
                    DevicePlayParam.getStopTime()
            );
            if (repository.existsByDevice(devicePlayParam.getDevice()) && smth) {
                errors.add(new FieldError(
                        "DevicePlayParam", "Device",
                        String.format("Device whit id: %s already has a playParam in this time period(from: %s till: %s)",
                                devicePlayParam.getDevice().getId(), devicePlayParam.getStartTime(), devicePlayParam.getStopTime())));
            }
        });


        if (devicePlayParam.getStopTime().isBefore(devicePlayParam.getStartTime())) {
            errors.add(new FieldError(
                    "DevicePlayParam", "StopTime", "Stop must be after startTime!"));
        }

        baseValidator.validateAnnotations(devicePlayParam, errors, "PlayParam");
    }

    boolean isOverlaping(LocalDateTime start1, LocalDateTime stop1, LocalDateTime start2, LocalDateTime stop2) {
        return start1.isBefore(stop2) && start2.isBefore(stop1);
    }
}
