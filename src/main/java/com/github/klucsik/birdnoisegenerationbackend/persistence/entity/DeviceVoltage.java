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

    private LocalDateTime createdAt = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String formated = createdAt.format(formatter);

}
