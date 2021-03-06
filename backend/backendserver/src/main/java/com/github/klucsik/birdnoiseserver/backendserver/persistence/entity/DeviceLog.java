package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class DeviceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Device device; //the device who logged.

    private Integer messageCode; //sent from device
    private String message; //decoded at BE
    private String additional;

    @DateTimeFormat(pattern = "yyyy MM dd HH:mm:ss")
    private LocalDateTime createdAt; //Time when created(saved)
    private Long timestamp; //epoch from device
    private LocalDateTime loggedTime; //time from the epoch timestamp
}
