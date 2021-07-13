package com.github.klucsik.birdnoisegenerationbackend.repository;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByChipId(String chipId);
}
