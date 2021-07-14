package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Entity
@Data
public class PlayParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(max = 255, message = "Name must be less characters than 255")
    private String name;

    @Range(min = 0, max = 15, message = "Volume must be between 0 and 15")
    @NotNull(message = "Volume cant be null")
    private Integer vol; //volume

    @ManyToMany
    private Map<Integer,PlayUnit> playUnits;
}
