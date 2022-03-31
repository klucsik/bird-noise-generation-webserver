package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayUnit;
import com.github.klucsik.birdnoiseserver.backendserver.repository.PlayParamRepository;
import com.github.klucsik.birdnoiseserver.backendserver.repository.PlayUnitRepository;
import com.github.klucsik.birdnoiseserver.backendserver.validators.PlayParamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PlayParamService {
    private final PlayParamRepository repository;
    private final PlayUnitRepository playUnitRepository;
    private final PlayParamValidator validator;

    public PlayParam save(PlayParam playParam) throws MethodArgumentNotValidException {
        Map<Integer, PlayUnit> playUnits = new HashMap<>();
        playParam.getPlayUnits().forEach(
                (hour, playUnitDto) -> {
                    int UTCHour = calcUtcHour(hour);
                    playUnits.put(UTCHour, playUnitRepository.findById(playUnitDto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("There is no PlayUnit with id: %d", playUnitDto.getId()))));
                }
        );
        playParam.setPlayUnits(playUnits);
        validator.validate(playParam);
        return repository.save(playParam);
    }

    public int calcUtcHour(Integer hour) {
        Calendar calendar = Calendar.getInstance();
        ZoneId zone = calendar.getTimeZone().toZoneId(); //Get the JVM timezone
        ZoneOffset zoneOffSet = zone.getRules().getOffset(LocalDateTime.now());
        int UTCHour = hour - zoneOffSet.getTotalSeconds() / 3600; //Hour - UTC difference in hours (TotalSecond/3600)
        if (UTCHour == 0) {
            UTCHour = 24;
        }
        if (UTCHour < 0) {
            UTCHour = 24 + UTCHour;
        }
        if (UTCHour > 24) {
            UTCHour = UTCHour - 24;
        }
        return UTCHour;
    }

    public PlayParam getOne(Long id) {
        Optional<PlayParam> playParam = repository.findById(id);
        if (playParam.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return playParam.get();
    }

    public List<PlayParam> getAll() {
        return new ArrayList<>(repository.findAll());
    }

    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("There is no playParam with id: %d", id)));
        repository.deleteById(id);
    }


}
