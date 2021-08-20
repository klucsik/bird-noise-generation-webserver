package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicePlayParamRepository extends JpaRepository<DevicePlayParam, Long> {
}
