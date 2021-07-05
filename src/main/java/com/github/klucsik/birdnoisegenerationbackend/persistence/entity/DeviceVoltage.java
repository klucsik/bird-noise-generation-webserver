package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@Data
public class DeviceVoltage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //db will generate this for us
    private Float voltage;
    @ManyToOne
    private Device device;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

}
