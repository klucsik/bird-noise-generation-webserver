package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.PlayParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PlayParamValidator {
    private final BaseValidator baseValidator;
    private final PlayParamRepository repository;

    public void validate(PlayParam playParam) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();

        if (repository.existsByName(playParam.getName())) {
            errors.add(new FieldError("PlayParam", "Name", "Name must be unique"));
        }

        baseValidator.validateAnnotations(playParam, errors, "PlayParam");
    }
}
