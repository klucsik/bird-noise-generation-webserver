package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceVoltage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceVoltageRepository extends JpaRepository<DeviceVoltage, Long> {
    List<DeviceVoltage> findAllByDevice(Device device);
}
