package com.github.klucsik.birdnoiseserver.backendclient.enums;


public enum DeviceLogContentTypes {
    MESSAGE("MESSAGE", "0"),
    ERROR("ERROR", "1"),
    TRACK_PLAYED("Track currently played","2"),
    PAUSE_TIME("PAUSE_TIME", "3"),
    RTC_NOW("RTC time now", "4"),
    INNER_NOW("Device inner time now","5");

    public final String label;
    public final String Number;

    DeviceLogContentTypes(String label, String Number) {
        this.label=label;
        this.Number=Number;
    }

    public static DeviceLogContentTypes valueOfNumber(String Number)  {
        for (DeviceLogContentTypes log : values()) {
            if (log.Number.equals(Number)) {
                return log;
            }
        }
        return null;
    }
}
