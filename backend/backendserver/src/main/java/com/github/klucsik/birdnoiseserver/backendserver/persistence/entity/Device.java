package com.github.klucsik.birdnoiseserver.backendserver.persistence.entity;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DeviceStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(max = 100)
    @NotBlank(message = "ChipId is mandatory")
    private String chipId;

    @Column(unique = true)
    @Size(max = 250)
    private String name;

    @Size(max = 250)
    private String location;

    @NotNull(message = "Status is mandatory")
    private DeviceStatus status; //TODO if there is no status do smth


}
