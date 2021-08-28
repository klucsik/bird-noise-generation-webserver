package com.github.klucsik.birdnoiseserver.backendserver.validators;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayUnit;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PlayUnitValidator{
    private final BaseValidator baseValidator;

    public void validate (PlayUnit playUnit) throws MethodArgumentNotValidException {
        List<FieldError> errors = new ArrayList<>();

        if (playUnit.getMinPause() != null && playUnit.getMaxPause() != null  && playUnit.getMinPause() > playUnit.getMaxPause()){
            errors.add(new FieldError("playUnit", "minPause",
                    "Minpause needs to be smaller or equal than maxpause!"));
        }

        Set<Track> trackSet = new HashSet<>();
        playUnit.getTrackList().forEach(track -> {
            if(!trackSet.add(track)){
                errors.add(new FieldError("PlayUnit","trackList",
                        String.format("TrackId %d is in the list more than one time!",track.getId())));
            }
        });
        //TODO if used in playParam than cant delete yeahhhhh

        baseValidator.validateAnnotations(playUnit,errors, "PlayUnit");
    }
}
