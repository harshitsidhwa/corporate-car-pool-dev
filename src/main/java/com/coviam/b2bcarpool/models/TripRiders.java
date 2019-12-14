package com.coviam.b2bcarpool.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TripRiders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String rideId;
    private String userId;
    private String allottedTripId;
    private String rideStatus;
    private Points pickupPoint;
    private Points destinationPoint;
    private Date rideStartTime;
    private Date rideEndTime;
    private double rideDistance;
    private int requestedSeats;
    private Date createdDate = new Date();
    private String createdBy;
}
