package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.AvailableRideInfoDTO;
import com.coviam.b2bcarpool.dto.RideDTO;

import java.util.List;

public interface FindRideService {
    List<AvailableRideInfoDTO> getBestMatchingRide(RideDTO requestContent);
}
