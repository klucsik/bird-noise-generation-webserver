package com.github.klucsik.birdnoisegenerationbackend.controller;

import com.github.klucsik.birdnoisegenerationbackend.dto.BatteryDto;
import com.github.klucsik.birdnoisegenerationbackend.dto.DeviceOverviewDto;
import com.github.klucsik.birdnoisegenerationbackend.persistence.enums.DeviceStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("deviceOverView")
@CrossOrigin
public class DeviceOverViewController {

    //this will be accessible at baseurl/devices/page
    @GetMapping("/page")
    public ResponseEntity<List<DeviceOverviewDto>> gridView(){
        //we will have aroud 50-100 devices. We will send all of them, and let the FE handle the pagination nad filtering.

        //mock some dto
        List<DeviceOverviewDto> responeList = new ArrayList<>();
        responeList.add(new DeviceOverviewDto(
                1L,
                "DeviceToLovePopó",
                new BatteryDto(1L,"battery1",3.7,"#008000"),
                DeviceStatus.OK.label
        ));
        responeList.add(new DeviceOverviewDto(
                2L,
                "Device2",
                new BatteryDto(1L,"battery2",3.6,"#FF0000"),
                DeviceStatus.OK.label
        ));
        responeList.add(new DeviceOverviewDto(
                3L,
                "Device3",
                new BatteryDto(1L,"battery2",null,"#FF0000"),
                DeviceStatus.NOT_RESPONDING.label
        ));

        return ResponseEntity.ok(responeList); //this will send a status code 200 and the dtolist in body as json
    }
}
