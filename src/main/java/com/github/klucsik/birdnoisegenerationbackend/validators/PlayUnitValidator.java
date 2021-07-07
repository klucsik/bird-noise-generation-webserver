package com.github.klucsik.birdnoisegenerationbackend.validators;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayUnit;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Track;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PlayUnitValidator extends BaseValidator{

    BaseValidator baseValidator;

    public PlayUnitValidator(Validator validator) {
        super(validator);
    }

    public void validate (PlayUnit playUnit) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();

        if (playUnit.getMinPause() >= playUnit.getMaxPause()){
            errors.add(new FieldError("playUnit", "minPause",
                    "Minpause needs to be smaller or equal than maxpause!"));
        }
        Set<Track> trackSet = new HashSet<>();
        playUnit.getTrackList().forEach(track -> {
            if(!trackSet.add(track)){
                errors.add(new FieldError("PlayUnit","trackList",
                        String.format("Tracknumber %d is in the list more than one time!",track.getTrackNumber())));
            }
        });

        baseValidator.validateAnnotations(playUnit,errors);
    }
}
