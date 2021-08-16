package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.PlayParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class PlayParamValidator {
    private final BaseValidator baseValidator;
    private final PlayParamRepository repository;

    public void validate(PlayParam playParam) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();

        if (repository.existsByName(playParam.getName())) {
            PlayParam existingPlayParam = repository.findByName(playParam.getName());
            if (playParam.getId() != null && existingPlayParam.getId() != playParam.getId()) {
                errors.add(new FieldError("PlayParam", "Name", "Name must be unique"));
            }
            else if (playParam.getId() == null) {
                errors.add(new FieldError("PlayParam", "Name", "Name must be unique"));
            }
        }

        playParam.getPlayUnits().forEach( (hour, playUnit) -> {
                    if (hour <= 0 || hour >= 25) {
                        errors.add(new FieldError("PlayParam", "Hour", "Hour must be between 0 and 24"));

                        Set<Integer> hourSet = new HashSet<>();
                        if (!hourSet.add(hour)) {
                            errors.add(new FieldError("PlayParam", "Hour", "Hour must be unique"));
                        }

                    }
        });

        baseValidator.validateAnnotations(playParam, errors, "PlayParam");
    }
}
