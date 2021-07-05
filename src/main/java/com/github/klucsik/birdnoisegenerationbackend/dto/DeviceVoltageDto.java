package com.github.klucsik.birdnoisegenerationbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class DeviceVoltageDto {
    private Long id;
    private Float voltage;
    private DeviceDto device;

    private LocalDateTime createdAt;
}
