package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.persistence.entity.DeviceVoltage;
import com.github.klucsik.birdnoisegenerationbackend.repository.DeviceVoltageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * This controller will handle the communication with the devices
 */
@RestController
@RequestMapping("/deviceApi")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceVoltageRepository deviceVoltageRepository;

    @GetMapping("/reportVoltage")
   public ResponseEntity getDeviceVoltage(@RequestParam(required = true) String deviceId, @RequestParam(required = true) Double batteryVoltage){
       //create and persist the object here
        DeviceVoltage deviceVoltage = new DeviceVoltage(deviceId,batteryVoltage, LocalDate.now());//TODO change it to localdatetime
        deviceVoltageRepository.save(deviceVoltage);
        System.out.println("deviceVoltage saved: " + deviceVoltage.toString());
       return new ResponseEntity(HttpStatus.OK);
   }

}
