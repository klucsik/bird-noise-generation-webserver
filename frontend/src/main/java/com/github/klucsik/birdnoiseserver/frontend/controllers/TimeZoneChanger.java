package com.github.klucsik.birdnoiseserver.frontend.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.LanguageCallback;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;

@RequiredArgsConstructor
@Service
public class TimeZoneChanger {
    public LocalDateTime changeTimeZoneToHUN(LocalDateTime dateTime) {
        LocalDateTime hunTime = dateTime.plusSeconds(ZoneId.of("Europe/Budapest").getRules().getOffset(LocalDateTime.now()).getTotalSeconds()); //This should work even with summerTime
        return hunTime;
    }
}
