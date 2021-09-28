package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DPPStatus;
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
        List<DevicePlayParam> allSavedDPP = repository.findByDeviceAndStatusNot(devicePlayParam.getDevice(), DPPStatus.DELETED);

        if (devicePlayParam.getDevice() == null | devicePlayParam.getPlayParam() == null) {
            errors.add(new FieldError("DevicePlayParam", "DevicePlayParam", "Please dont leave anything empty"));
        }

        else {
            allSavedDPP.forEach(savedDevicePlayParam -> {
                if (devicePlayParam.getId() != null & savedDevicePlayParam.getId() == devicePlayParam.getId()) {
                    return; //skip over itself if it meets
                }

                if (repository.existsByDevice(devicePlayParam.getDevice()) &&
                        isOverlaping(
                                devicePlayParam.getStartTime(), devicePlayParam.getStopTime(),
                                savedDevicePlayParam.getStartTime(), savedDevicePlayParam.getStopTime()
                        )
                ) {
                    errors.add(new FieldError(
                            "DevicePlayParam", "Device",
                            String.format("Device with id: %s already has a playParam in this time period(from: %s till: %s). It's id is: %d",
                                    devicePlayParam.getDevice().getId(), devicePlayParam.getStartTime(), devicePlayParam.getStopTime(),savedDevicePlayParam.getId())));
                }
            });


            if (devicePlayParam.getStopTime().isBefore(devicePlayParam.getStartTime())) {
                errors.add(new FieldError(
                        "DevicePlayParam", "StopTime", "Stop must be after startTime!"));
            }
        }
        baseValidator.validateAnnotations(devicePlayParam, errors, "PlayParam");
    }

    boolean isOverlaping(LocalDateTime start1, LocalDateTime stop1, LocalDateTime start2, LocalDateTime stop2) {
        return start1.isBefore(stop2) && start2.isBefore(stop1);
    }
}
