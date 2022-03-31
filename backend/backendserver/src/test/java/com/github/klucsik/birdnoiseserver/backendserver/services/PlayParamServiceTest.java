package com.github.klucsik.birdnoiseserver.backendserver.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SystemStubsExtension.class)
class PlayParamServiceTest {
    private AutoCloseable closeable;

    @InjectMocks
    private PlayParamService playParamService;

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
        assertEquals(22, playParamService.calcUtcHour(0));
        assertEquals(23, playParamService.calcUtcHour(1));
        assertEquals(8, playParamService.calcUtcHour(10));
        assertEquals(22, playParamService.calcUtcHour(24));
    }
}