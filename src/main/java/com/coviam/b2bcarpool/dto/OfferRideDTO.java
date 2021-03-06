package com.coviam.b2bcarpool.dto;

import com.coviam.b2bcarpool.models.Points;
import lombok.Data;

import java.util.Date;

@Data
public class OfferRideDTO {

    private String vehicleNumber;
    private Points pickupPoint;
    private Points destinationPoint;
    private Date tripStartTime;
    private int offeredSeats;

    @Override
    public String toString() {
        return "OfferRideDTO{" +
                "carNumber='" + vehicleNumber + '\'' +
                ", pickupPoint=" + pickupPoint +
                ", destinationPoint=" + destinationPoint +
                ", tripStartTime=" + tripStartTime +
                ", offeredSeats=" + offeredSeats +
                '}';
    }
}
