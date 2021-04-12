package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.PlayUnitDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.PlayUnitMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayUnit;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Track;
import com.github.klucsik.birdnoisegenerationbackend.repository.PlayUnitRepository;
import com.github.klucsik.birdnoisegenerationbackend.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayUnitService {
    private final PlayUnitRepository repository;
    private final TrackRepository trackRepository;


    public PlayUnitDto save(PlayUnitDto dto){
        PlayUnit playUnit = PlayUnitMapper.MAPPER.DtoToPlayUnit(dto);
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
