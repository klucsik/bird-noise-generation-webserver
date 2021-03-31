package com.github.klucsik.birdnoisegenerationbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TrackDto {
    private Long id;
    private Integer trackNumber;
    private String name;
    private Integer length;
}
