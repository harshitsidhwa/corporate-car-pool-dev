package com.coviam.b2bcarpool.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Trips {

    @Id
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
