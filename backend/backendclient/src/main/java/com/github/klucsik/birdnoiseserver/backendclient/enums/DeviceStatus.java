package com.github.klucsik.birdnoiseserver.backendclient.enums;


public enum DeviceStatus {
    OK("Ok"),
    NOT_RESPONDING("Not Responding"),
    UPDATING("Updating"),
    NEW("New"),
    UNREGISTERED("Unregistered");

    public final String label;

    DeviceStatus(String label) {
        this.label=label;
    }
}
