package com.github.klucsik.birdnoisegenerationbackend.repository;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.DeviceVoltage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceVoltageRepository extends JpaRepository<DeviceVoltage, Long> {
}
