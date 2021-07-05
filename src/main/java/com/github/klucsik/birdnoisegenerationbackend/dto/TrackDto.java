package com.github.klucsik.birdnoisegenerationbackend.dto;

import com.github.klucsik.birdnoisegenerationbackend.services.TrackService;
import com.github.klucsik.birdnoisegenerationbackend.validators.Unique;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the Track entity for the communication with the frontend.
 */
@NoArgsConstructor
@Data
public class TrackDto {
    private Long id;

    @NotNull(message = "Tracknumber is mandatory") //Validations always should be the same as on Entity
    @Range(min = 1, max = 255)
    @Unique(service = TrackService.class, fieldName = "trackNumber", message = "Track number must be unique!")
    private Integer trackNumber;

    @Size(max=255)
    @NotBlank(message = "Name is mandatory")
    @Unique(service = TrackService.class, fieldName = "name", message = "Track name must be unique!")

    private String name;

    @Range(min = 1,max = 1800)
    @NotNull(message = "Length is mandatory")
    private Integer length;

   public TrackDto(Integer trackNumber,String name,Integer length){
        this.trackNumber=trackNumber;
        this.name=name;
        this.length=length;
    }
}
