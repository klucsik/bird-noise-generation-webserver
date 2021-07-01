package com.github.klucsik.birdnoisegenerationbackend.dto;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class DeviceVoltageDto {
    private Long id;
    private Float voltage;

    @ManyToMany
    private List<DeviceDto> chipDto;
    private LocalDate createdAt;
}
