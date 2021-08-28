package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayUnitRepository extends JpaRepository<PlayUnit, Long> {
    PlayUnit findByName(String name);

    Boolean existsByName(String name);
}
