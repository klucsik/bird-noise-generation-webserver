package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class DevicePlayParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Device can't be null")
    private Device device;

    @ManyToOne
    @NotNull(message = "PlayParam can't be null")
    private PlayParam playParam;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime stopTime;
}
