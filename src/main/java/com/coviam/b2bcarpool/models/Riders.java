package com.coviam.b2bcarpool.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Riders {

    @Id
    private String rideId;
    private String userId;
    private String allottedTripId;
    private String rideStatus;
    private Points pickupPoint;
    private Points destinationPoint;
    private Date rideStartTime;
    private Date rideEndTime;
    private double rideDistance;
    private int requestedSeats = 1;
    private Date createdDate = new Date();
    private String createdBy;
    private boolean isRiderTripOwner;
}
