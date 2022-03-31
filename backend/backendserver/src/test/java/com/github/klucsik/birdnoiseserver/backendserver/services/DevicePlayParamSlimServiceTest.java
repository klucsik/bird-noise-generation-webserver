package com.github.klucsik.birdnoiseserver.backendserver.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class DevicePlayParamSlimServiceTest {

    private AutoCloseable closeable;

    @InjectMocks
    private DevicePlayParamSlimService service;

    @BeforeEach
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void calcUtcHour() {
        Calendar calendar = Calendar.getInstance();
        assertEquals(calendar.getTimeZone(), TimeZone.getTimeZone("Europe/Budapest"));
        assertEquals(22, service.calcUtcHour(0));
        assertEquals(23, service.calcUtcHour(1));
        assertEquals(8, service.calcUtcHour(10));
        assertEquals(22, service.calcUtcHour(24));
    }
}