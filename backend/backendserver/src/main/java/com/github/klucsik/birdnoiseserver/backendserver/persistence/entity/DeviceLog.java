package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
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

    private String contentCode; //whats happenin' should be done with enum

    private String message; //'Decoded' with *insert enum name here*

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt; //Time when created(saved)

    private String loggedTime; //Time from log params form device right now string should be localdatetime?
}
