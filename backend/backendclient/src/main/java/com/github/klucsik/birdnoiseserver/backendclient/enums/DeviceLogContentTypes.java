package com.github.klucsik.birdnoiseserver.backendclient.enums;


public enum DeviceLogContentTypes {
    MESSAGE("MESSAGE", "0"),
    ERROR("ERROR", "1"),
    TRACK_PLAYED("Track currently played - track length","2"),
    PAUSE_TIME("paused for secs", "3"),
    RTC_NOW("RTC time now", "4"),
    INNER_NOW("Device inner time now","5"),
    NEW_PLAYPARAM_VER("Playparam  synced, new playparam version","6"),
    START_UP("Device started or woke up with version","7"),
    DEEP_SLEEP("Device goind to sleep now to seconds","9"),
    CURRENT_PLAYPARAM("Playparam for this hour", "10"),
    DF_PLAYER_MESSAGE("DFPlayer chip, messagebytes", "11"),
    ;


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
