package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayParamRepository extends JpaRepository<PlayParam,Long> {
    boolean existsByName(String name);
}
