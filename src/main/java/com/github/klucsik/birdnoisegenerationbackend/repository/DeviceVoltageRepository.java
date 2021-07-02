package com.github.klucsik.birdnoisegenerationbackend.repository;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.DeviceVoltage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceVoltageRepository extends JpaRepository<DeviceVoltage, Long> {
    List<DeviceVoltage> findAllByDevice(Device device);
}
