package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Track;
import com.github.klucsik.birdnoiseserver.backendserver.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrackValidator {
    private final BaseValidator baseValidator;
    private final TrackRepository repository;

    public void validate(Track track) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();

        if (repository.existsByName(track.getName())) {
            errors.add(new FieldError("Track", "name", "Name must be unique!"));
        }
        if (repository.existsByTrackNumber(track.getTrackNumber())) {
            errors.add(new FieldError("Track", "trackNumber", "Track number must be unique!"));
        }

        baseValidator.validateAnnotations(track, errors, "Track");
    }
}
