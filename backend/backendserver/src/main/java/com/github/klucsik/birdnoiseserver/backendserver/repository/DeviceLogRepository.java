package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceLogRepository extends JpaRepository<DeviceLog,Long> {
    List<DeviceLog> findAllByDevice(Device device);
}
