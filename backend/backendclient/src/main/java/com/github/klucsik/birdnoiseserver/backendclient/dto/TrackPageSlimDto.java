package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TrackPageSlimDto {
    private List<TrackSlimDto> tracklengths;

}
