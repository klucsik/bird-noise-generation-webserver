package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class DeviceVoltage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; //db will generate this for us
    private String deviceId; //TODO add a relationship on the parent
    private Double voltage;

    private LocalDate createdAt; //we will set this in the service layer

    public DeviceVoltage(String deviceId, Double voltage, LocalDate createdAt){
        this.deviceId=deviceId;
        this.voltage=voltage;
        this.createdAt=createdAt;
    }

}
