package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

import com.github.klucsik.birdnoisegenerationbackend.persistence.enums.DeviceStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceId;
    private String name;
    private String location;
    private DeviceStatus status;


}
