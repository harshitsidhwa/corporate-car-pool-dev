package com.coviam.b2bcarpool.models.enums;

public enum RideStatusEnum {
    ALLOTTED_STATUS("ALLOTTED"),
    CANCELLED_STATUS("CANCELLED"),
    COMPLETED_STATUS("COMPLETED"),
    REQUESTED_STATUS("REQUESTED");

    private String rideStatus;

    RideStatusEnum(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getRideStatus() {
        return rideStatus;
    }
}
