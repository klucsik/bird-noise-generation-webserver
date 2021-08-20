package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class DevicePlayParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Device device;

    @ManyToOne
    private PlayParam playParam;

    private LocalDateTime startTime;
    private LocalDateTime stopTime;
}
