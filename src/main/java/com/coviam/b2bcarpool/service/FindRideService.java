package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.RideDTO;

import java.util.List;

public interface FindRideService {
    List<RideDTO> getBestMatchingRide(RideDTO requestContent);

    boolean insertRideToTrip(String riderUserId, RideDTO requestContent);
}
