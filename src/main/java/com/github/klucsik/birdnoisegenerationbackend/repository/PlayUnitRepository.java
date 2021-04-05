package com.github.klucsik.birdnoisegenerationbackend.repository;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayUnitRepository extends JpaRepository<PlayUnit, Long> {
}
