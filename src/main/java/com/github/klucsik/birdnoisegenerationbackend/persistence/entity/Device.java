package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

import com.github.klucsik.birdnoisegenerationbackend.persistence.enums.DeviceStatus;
import com.github.klucsik.birdnoisegenerationbackend.validators.annotations.Unique;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chipId;
    private String name;
    private String location;
    private DeviceStatus status;


}
