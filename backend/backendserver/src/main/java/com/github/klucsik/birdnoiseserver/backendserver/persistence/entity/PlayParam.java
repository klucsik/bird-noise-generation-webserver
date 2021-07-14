package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
public class PlayParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer vol; //volume
    @ManyToMany
    private Map<Integer,PlayUnit> playUnits;
}
