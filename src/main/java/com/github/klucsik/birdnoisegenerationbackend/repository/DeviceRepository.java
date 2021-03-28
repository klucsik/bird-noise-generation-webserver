package com.github.klucsik.birdnoisegenerationbackend.repository;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
