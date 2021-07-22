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
            errors.add(new FieldError("PlayParam", "Name", "Name must be unique"));
        }


        playParam.getPlayUnits().forEach( (hour, playUnit) -> {//FIXME there are problems at update
                    if (hour <= 0 || hour >= 25) {
                        errors.add(new FieldError("PlayParam", "Hour", "Hour must be between 0 and 24"));

                        Set<Integer> hourSet = new HashSet<>();
                        if (!hourSet.add(hour)) { //FIXME there are problems at update
                            errors.add(new FieldError("PlayParam", "Hour", "Hour must be unique"));
                        }

                    }
                });

        baseValidator.validateAnnotations(playParam, errors, "PlayParam");
    }
}
