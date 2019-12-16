package com.coviam.b2bcarpool.dto;

import com.coviam.b2bcarpool.models.Points;
import lombok.Data;

import java.util.Date;

@Data
public class TripBasicInfoDTO {
    private String userId;
    private String tripId;
    private String profilePic;
    private String fullName;
    private String vehicleNumber;
    private Points pickupPoint;
    private Points destinationPoint;
    private Date tripStartTime;
    private int availableSeats;
    private String phoneNumber;
}
