package com.github.klucsik.birdnoiseserver.backendserver.repository;

import com.github.klucsik.birdnoiseserver.backendclient.enums.DPPStatus;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevicePlayParamRepository extends JpaRepository<DevicePlayParam, Long> {
    List<DevicePlayParam> findByDeviceAndStatusNot(Device device, DPPStatus status);
    List<DevicePlayParam> findByDeviceAndStatusNotAndStatusNot(Device device, DPPStatus status1, DPPStatus status2);

    DevicePlayParam findByPlayParam(PlayParam playParam);

    boolean existsByDevice(Device device);
}
