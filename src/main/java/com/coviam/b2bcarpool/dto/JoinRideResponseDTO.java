package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class JoinRideResponseDTO {
    private boolean rideJoined;
    private String errMsg;
}
