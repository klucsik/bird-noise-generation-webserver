package com.github.klucsik.birdnoisegenerationbackend.repository;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.BatteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<BatteryEntity, Long> {
}
