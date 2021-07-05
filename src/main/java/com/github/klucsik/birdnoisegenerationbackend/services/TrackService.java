package com.github.klucsik.birdnoisegenerationbackend.services;

import com.github.klucsik.birdnoisegenerationbackend.dto.TrackDto;
import com.github.klucsik.birdnoisegenerationbackend.mappers.TrackMapper;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Track;
import com.github.klucsik.birdnoisegenerationbackend.repository.TrackRepository;
import com.github.klucsik.birdnoisegenerationbackend.validators.FieldValueExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackService implements FieldValueExists {
    private final TrackRepository repository;


    //CREATE & UPDATE
    public TrackDto save(TrackDto dto) {
        @Valid Track track = TrackMapper.MAPPER.DtoToTrack(dto);
        return TrackMapper.MAPPER.trackToDto(repository.save(track));
    }

    //READ
    public TrackDto getOne(Long id){
        Optional<Track> track = repository.findById(id);
        if (track.isEmpty()){ //this is a guard clause, its a good way to put validation in the front of the method. makes it faster to execute when running in an inavlid input
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return TrackMapper.MAPPER.trackToDto(track.get());
    }

    public List<TrackDto> getAll(){  //There will be around 10-20 items so we will not implement page
        return repository.findAll().stream().map(TrackMapper.MAPPER::trackToDto).collect(Collectors.toList()); //the stream is a handy way to do mass operations in a list of objects.
        //With stream.map you will create a new list with an equal size, making new elements with whatever you put inside the map function.
        //Here I have put a reference for a method which give takes the Track type, and gives back the TrackDto type.
        //Another valid syntax is .map(track -> mapper.trackToDto(track)), here you can use the track variable to do the conversion. the map() will give you a new track on each iteration.
    }

    //DELETE
    public  void delete(Long id){
        Optional<Track> track = repository.findById(id);
        if (track.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }


    /**
     * @param value     The value to check for
     * @param fieldName The name of the field for which to check if the value exists
     * @return true if fieldname is unique
     */
    @Override
    public boolean fieldValueExists(Object value, @NotNull String fieldName) throws UnsupportedOperationException {

        if (value == null) {
            return false;
        }

        switch (fieldName) {
            case "trackNumber":
                return this.repository.existsByTrackNumber((Integer) value);

            case "name":
                return this.repository.existsByName(value.toString());

            default:
                throw new UnsupportedOperationException("Field name not supported");
        }


    }

}
