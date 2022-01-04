package com.github.klucsik.birdnoiseserver.backendclient.enums;

public enum DeviceLogContentTypes {
    MESSAGE("MESSAGE", "0"),
    ERROR("ERROR", "1");

    public final String label;
    public final String Number;

    DeviceLogContentTypes(String label, String Number) {
        this.label=label;
        this.Number=Number;
    }

    public static DeviceLogContentTypes valueOfNumber(String Number) {
        for (DeviceLogContentTypes log : values()) {
            if (log.Number.equals(Number)) {
                return log;
            }
        }
        return null;
    }
}
