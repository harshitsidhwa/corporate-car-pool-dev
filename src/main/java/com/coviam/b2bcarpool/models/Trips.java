package com.coviam.b2bcarpool.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Trips {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tripId;
    private String tripStatus;
    private String userId;
    private Points pickupPoint;
    private Points destinationPoint;
    private Date tripStartTime;
    private Date tripEndTime;
    private int offeredSeats;
    private int currSeats = 0;
    private double pricePerKm;
    private List<TripRiders> joinedRiders;
    private Date createdDate = new Date();
    private String createdBy;
}
