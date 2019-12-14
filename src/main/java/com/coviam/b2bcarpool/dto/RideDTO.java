package com.coviam.b2bcarpool.dto;

import com.coviam.b2bcarpool.models.Points;
import lombok.Data;

import java.util.Date;

@Data
public class RideDTO {
    private String userId;
    private Points pickupPoint;
    private Points destinationPoint;
    private Date rideStartTime;
    private int requestedSeats;
}
