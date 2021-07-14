package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

import com.github.klucsik.birdnoisegenerationbackend.validators.annotations.Trimmed;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Tracknumber is mandatory") //Validations always should be the same as on Dto
    @Range(min = 1, max = 255)
    @Column(unique = true) //this is validated at the TrackValidator, but here lets have the DB enforce it
    private Integer trackNumber; //the track on the devices sd card is only identified with a number

    @Size(max = 255)
    @NotBlank(message = "Name is mandatory")
    @Trimmed
    @Column(unique = true)
    private String name;

    @Range(min = 1, max = 1800)
    @NotNull(message = "Length is mandatory")
    private Integer length;
}
