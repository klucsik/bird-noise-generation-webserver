package com.github.klucsik.birdnoisegenerationbackend.persistence.enums;


public enum DeviceStatus {
    WORKING("Working"),
    NOT_RESPONDING("Not Responding");

    public final String label;

    DeviceStatus(String label) {
        this.label=label;
    }
}
