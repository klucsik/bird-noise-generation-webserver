package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.PlayUnitDto;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayUnitMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayUnit;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Track;
import com.github.klucsik.birdnoiseserver.backendserver.repository.PlayUnitRepository;
import com.github.klucsik.birdnoiseserver.backendserver.repository.TrackRepository;
import com.github.klucsik.birdnoiseserver.backendserver.validators.PlayUnitValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayUnitService {
    private final PlayUnitRepository repository;
    private final TrackRepository trackRepository;
    private final PlayUnitValidator validator;

    public PlayUnitDto save(PlayUnitDto dto) throws MethodArgumentNotValidException {
        PlayUnit playUnit = PlayUnitMapper.MAPPER.DtoToPlayUnit(dto);

        validator.validate(playUnit); //We only need to call the validator here.

        //we expect to get the ids of the tracks, we find the tracks from the ids, and set them for the new entity. We wont accept nonexisting track-ids.
        List<Track> trackListFromDto = playUnit.getTrackList().stream().map(dtoTrack -> trackRepository.findById(dtoTrack.getId()).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("There is no track with id: %d", dtoTrack.getId())))).collect(Collectors.toList()); //FIXME: isn't this should be done on the dto tracklist?
        playUnit.setTrackList(trackListFromDto);
        return PlayUnitMapper.MAPPER.playUnitToDto(repository.save(playUnit));
    }


    public PlayUnitDto getOne(Long id){
        Optional<PlayUnit> playUnit = repository.findById(id);
        if(playUnit.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return PlayUnitMapper.MAPPER.playUnitToDto(playUnit.get());
    }

    public List<PlayUnitDto> getAll(){
        return repository.findAll().stream()
                .map(PlayUnitMapper.MAPPER::playUnitToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        PlayUnit playUnit = repository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("There is no playUnit with id: %d", id)));
        repository.deleteById(id);
    }

}
