package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevicePlayParamRepository extends JpaRepository<DevicePlayParam, Long> {
    List<DevicePlayParam> getAllByDevice(Device device);

    DevicePlayParam getByDevice(Device device);

    boolean existsByDevice(Device device);
}
