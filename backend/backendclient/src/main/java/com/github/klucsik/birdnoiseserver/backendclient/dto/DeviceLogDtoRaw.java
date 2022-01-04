package com.github.klucsik.birdnoiseserver.backendclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceLogDtoRaw { //quite literally the raw log u get from the device
    private String timestamp; //Time of the creation of log on device might need mapping to localdatetime idk how

    private String contentCode; //what type of message is it? Message/Error/Track_Played stb.

    private String message; //The content of the message
}
