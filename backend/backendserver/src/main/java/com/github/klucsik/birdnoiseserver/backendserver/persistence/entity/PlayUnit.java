package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class PlayUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "minPause is mandatory") //Validations always should be the same as on Dto
    @Range(min = 0, max = 3600)
    private Integer minPause;
    @NotNull(message = "maxPause is mandatory") //Validations always should be the same as on Dto
    @Range(min = 0, max = 3600)
    private Integer maxPause;
    @Size(min=1,max=25)
    @ManyToMany
    private List<Track> trackList;
}
