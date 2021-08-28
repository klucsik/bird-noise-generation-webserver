package com.github.klucsik.birdnoiseserver.frontend.stupidDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FrontEndDevicePlayParamDto {
    private Long id;
    private Long playParam;
    private Long device;
    private String startTime;
    private String stopTime;
}
