package com.coviam.b2bcarpool.models.enums;

public enum TripStatusEnum {
    ACTIVE_STATUS("ACTIVE"),
    CANCELLED_STATUS("CANCELLED"),
    COMPLETED_STATUS("COMPLETED"),
    FILLED_STATUS("FILLED"),
    STARTED_STATUS("STARTED");

    private String tripStatus;

    TripStatusEnum(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getTripStatus() {
        return tripStatus;
    }
}
