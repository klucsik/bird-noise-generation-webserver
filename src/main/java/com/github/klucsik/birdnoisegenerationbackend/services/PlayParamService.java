package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.PlayParamDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.PlayUnitDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.TrackDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.PlayParamMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayParam;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayUnit;
import com.github.klucsik.birdnoisegenerationbackend.repository.PlayParamRepository;
import com.github.klucsik.birdnoisegenerationbackend.repository.PlayUnitRepository;
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

    private final TrackService trackService; //DELETEME
    private final PlayUnitService playUnitService; //DELETEME

    public PlayParamDto save(PlayParamDto dto) {
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

    public PlayParamDto getMock() throws MethodArgumentNotValidException {
        TrackDto track1Dto = trackService.save(new TrackDto(1, "Teszt track 1", 210));
        List<TrackDto> trackDtoList = new ArrayList<>();
        trackDtoList.add(track1Dto);
        PlayUnitDto playUnitDto = playUnitService.save(new PlayUnitDto(10, 15, trackDtoList));
        //When
        Map<Integer, PlayUnitDto> playUnits = new HashMap<>();
        playUnits.put(1, playUnitDto);
        PlayParamDto playParamDto = new PlayParamDto(-1L, "Teszt PlayParams 1", 11,playUnits);
        return save(playParamDto);
    }

}
