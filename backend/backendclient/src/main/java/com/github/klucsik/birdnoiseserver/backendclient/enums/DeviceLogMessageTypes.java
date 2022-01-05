package com.github.klucsik.birdnoiseserver.backendclient.enums;


public enum DeviceLogMessageTypes {
    MESSAGE("DFPLAYER_START_ERROR", "1"),
    ERROR("FILE_OPEN_ERROR", "2");

    public final String label;
    public final String Number;

    DeviceLogMessageTypes(String label, String Number) {
        this.label=label;
        this.Number=Number;
    }

    public static DeviceLogMessageTypes valueOfNumber(String Number)  {
        for (DeviceLogMessageTypes log : values()) {
            if (log.Number.equals(Number)) {
                return log;
            }
        }
        return null;
    }
}
