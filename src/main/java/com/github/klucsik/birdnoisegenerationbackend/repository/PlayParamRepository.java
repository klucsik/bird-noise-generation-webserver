package com.github.klucsik.birdnoisegenerationbackend.repository;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.PlayParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayParamRepository extends JpaRepository<PlayParam,Long> {
}
