package com.github.klucsik.birdnoisegenerationbackend.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class DeviceVoltage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; //db will generate this for us
    private Float voltage;

    @ManyToMany
    private List<Device> chip;
    private LocalDate createdAt; //we will set this in the service layer

}
