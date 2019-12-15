package com.coviam.b2bcarpool.service;

import java.util.List;

import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;

public interface MyRidesService {

    List<RideBasicInfoDTO> getUpcomingRides(String riderUserId);

    List<RideBasicInfoDTO> getHistoryRides(String rideUserId);
}
