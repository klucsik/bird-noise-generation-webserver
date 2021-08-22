package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.TrackDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayParamService {
    private final PlayParamRepository repository;
    private final PlayUnitRepository playUnitRepository;
    private final PlayParamValidator validator;

    private final TrackService trackService; //DELETEME
    private final PlayUnitService playUnitService; //DELETEME

    public PlayParamDto save(PlayParamDto dto) throws MethodArgumentNotValidException {
        PlayParam playParam = PlayParamMapper.MAPPER.dtoToPlayParam(dto);
        Map<Integer, PlayUnit> playUnits = new HashMap<>();
        dto.getPlayUnits().forEach(
                (hour, playUnitDto) -> {
                    if (!playUnitRepository.existsById(playUnitDto.getId())) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("There is no PlayUnit with id: %d", playUnitDto.getId()));
                    }
                    playUnits.put(hour, playUnitRepository.getOne(playUnitDto.getId()));
                }
        );
        playParam.setPlayUnits(playUnits);
        validator.validate(playParam);
        return PlayParamMapper.MAPPER.playParamtoDto(repository.save(playParam));
    }

    public PlayParamDto getOne(Long id) {
        Optional<PlayParam> playParam = repository.findById(id);
        if (playParam.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return PlayParamMapper.MAPPER.playParamtoDto(playParam.get());
    }

    public List<PlayParamDto> getAll() {
        return repository.findAll().stream()
                .map(PlayParamMapper.MAPPER::playParamtoDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        PlayParam playParam = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("There is no playParam with id: %d", id)));
        repository.deleteById(id);
    }
}
