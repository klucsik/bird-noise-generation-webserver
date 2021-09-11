package com.github.klucsik.birdnoiseserver.backendserver.services;

import com.github.klucsik.birdnoiseserver.backendclient.dto.DeviceDto;
import com.github.klucsik.birdnoiseserver.backendclient.dto.DevicePlayParamDto;
import com.github.klucsik.birdnoiseserver.backendclient.enums.DPPStatus;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DeviceMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.DevicePlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.mappers.PlayParamMapper;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.Device;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.DevicePlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.persistence.entity.PlayParam;
import com.github.klucsik.birdnoiseserver.backendserver.repository.DevicePlayParamRepository;
import com.github.klucsik.birdnoiseserver.backendserver.validators.DevicePlayParamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevicePlayParamService {
    private final PlayParamService playParamService;
    private final DeviceService deviceService;
    private final DevicePlayParamRepository repository;
    private final DevicePlayParamValidator validator;

    public DevicePlayParam save(DevicePlayParam devicePlayParam) throws MethodArgumentNotValidException {

        devicePlayParam.setDevice(deviceService.GetById(devicePlayParam.getDevice().getId()));
        devicePlayParam.setPlayParam(playParamService.getOne(devicePlayParam.getPlayParam().getId()));

        validator.validate(devicePlayParam);

        return repository.save(devicePlayParam);
    }

    public DevicePlayParam getById(Long id) {
        DevicePlayParam devicePlayParam = repository.getOne(id);
        return devicePlayParam;
    }

    public List<DevicePlayParam> getAllByDevice(Long id) {
        Device device = deviceService.GetById(id);
        return repository.findByDeviceAndStatusNot(device, DPPStatus.DELETED).stream().collect(Collectors.toList());
    }

    public List<DevicePlayParam> getPage() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public String setToDeployable(Long deviceId) {
        Device device = deviceService.GetById(deviceId);
        DevicePlayParam devicePlayParam = repository.findByDevice(device);
        devicePlayParam.setStatus(DPPStatus.DEPLOYABLE);
        repository.save(devicePlayParam);
        return "Set status to Deployable";
    }

    public String setToDraft(Long deviceId) {
        Device device = deviceService.GetById(deviceId);
        DevicePlayParam devicePlayParam = repository.findByDevice(device);
        devicePlayParam.setStatus(DPPStatus.DRAFT);
        repository.save(devicePlayParam);
        return "Set status to Draft";
    }

    public String setToDeleted(Long deviceId) {
        Device device = deviceService.GetById(deviceId);
        DevicePlayParam devicePlayParam = repository.findByDevice(device);
        devicePlayParam.setStatus(DPPStatus.DELETED);
        repository.save(devicePlayParam);
        return "Set status to Deleted";
    }
}
