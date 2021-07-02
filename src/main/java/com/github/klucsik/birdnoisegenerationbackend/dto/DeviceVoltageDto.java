package com.github.klucsik.birdnoisegenerationbackend.dto;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DeviceVoltageDto {
    private Long id;
    private Float voltage;
    private DeviceDto device;

    private LocalDate createdAt;
}
