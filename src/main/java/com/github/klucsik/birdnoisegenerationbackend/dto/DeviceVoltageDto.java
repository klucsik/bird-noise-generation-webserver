package com.github.klucsik.birdnoisegenerationbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class DeviceVoltageDto {
    private Long id;
    private Float voltage;
    private DeviceDto device;

    private LocalDate createdAt; //a createdTimenak ezt is tartalmaznia kéne. Egy fieldben legyen a teljes dátum (használhatsz formattereket: https://www.geeksforgeeks.org/localtime-format-method-in-java-with-examples/#:~:text=The%20format()%20method%20of,passed%20formatter%20to%20a%20string.)
    private LocalTime createdTime;
}
