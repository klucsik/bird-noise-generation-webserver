package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

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
    @Column(unique = true)
    //TODO: This tell rthe sql server to cause an 500 on duplicates. Lets make a custom validator in the service to handle this gracefully
    private Integer trackNumber; //the track on the devices sd card is only identified with a number

    @Size(max = 255)
    @NotBlank(message = "Name is mandatory")
    @Column(unique = true)
    private String name;

    @Range(min = 1, max = 1800)
    @NotNull(message = "Length is mandatory")
    private Integer length;
}
