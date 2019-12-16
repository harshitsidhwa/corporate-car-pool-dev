package com.coviam.b2bcarpool.dto;

import com.coviam.b2bcarpool.models.Points;
import lombok.Data;

@Data
public class RideBasicInfoDTO {
    //for My trips listing page
    private String userId;
    private String tripId;
    private Points pickupPoint;
    private Points destinationPoint;
    private String rideStartTime;
    private String seats;
    private String tripStatus;
    private String vehicleNumber;
}
