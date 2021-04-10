package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class PlayUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer minPause;
    private Integer maxPause;
    @ManyToMany
    private List<Track> trackList;
}
