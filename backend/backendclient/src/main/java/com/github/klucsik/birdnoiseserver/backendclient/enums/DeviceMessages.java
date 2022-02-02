package com.github.klucsik.birdnoiseserver.backendclient.enums;


public enum DeviceMessages {
    MESSAGE("MESSAGE", "0", false),
    ERROR("ERROR", "1", false),
    TRACK_PLAYED("Track currently played - track length","2", false),
    PAUSE_TIME("paused for secs", "3", false),
    RTC_NOW("RTC time now", "4", false),
    INNER_NOW("Device inner time now","5", false),
    NEW_PLAYPARAM_VER("Playparam  synced, new playparam version","6", false),
    START_UP("Device started or woke up with version","7", false),
    DEEP_SLEEP("Device going to sleep now to minutes","9", false),
    CURRENT_PLAYPARAM("Playparam for this hour", "10", false),
    DF_PLAYER_MESSAGE("DFPlayer chip messagebytes", "11", false),

    //ERRORS
    DFPLAYER_START_ERROR("Error starting mp3 module", "91", true),
    FILE_OPEN_ERROR("Error opening file", "92", true),
    FILE_WRITE_FAIL("Error writing file", "93", true),
    PLAYPARAM_INVALID("invalid playparam", "94", true),
    TRACKLENGTH_INVALID("invalid tracklength", "95", true),
    ;


    public final String label;
    public final String messageCode;
    public final Boolean error;

    DeviceMessages(String label, String messageCode, Boolean error) {
        this.label=label;
        this.messageCode =messageCode;
        this.error = error;
    }

    public static DeviceMessages MessageCodetoHumanReadable(String messageCode)  {
        for (DeviceMessages log : values()) {
            if (log.messageCode.equals(messageCode)) {
                return log;
            }
        }
        return null;
    }
}
