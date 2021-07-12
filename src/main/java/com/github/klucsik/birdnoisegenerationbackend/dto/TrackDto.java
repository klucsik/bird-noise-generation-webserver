package com.github.klucsik.birdnoisegenerationbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the Track entity for the communication with the frontend.
 */
@NoArgsConstructor
@Data
public class TrackDto {
    private Long id;
    private Integer trackNumber;
    private String name;
    private Integer length;

    public TrackDto(Integer trackNumber, String name, Integer length) {
        this.trackNumber = trackNumber;
        this.name = name;
        this.length = length;
    }
}
