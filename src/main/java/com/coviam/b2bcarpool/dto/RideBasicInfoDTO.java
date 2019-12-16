package com.coviam.b2bcarpool.dto;

import com.coviam.b2bcarpool.models.Points;
import lombok.Data;

import java.util.Date;

@Data
public class RideBasicInfoDTO {
    //for My trips listing page
    private String userId;
    private String tripId;
    private Points pickupPoint;
    private Points destinationPoint;
    private Date rideStartTime;
    private String seats;
    private String tripStatus;
    private String vehicleNumber;
}
