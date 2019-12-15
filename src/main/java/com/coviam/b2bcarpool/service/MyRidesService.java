package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;
import com.coviam.b2bcarpool.dto.RideDTO;

import java.util.List;

public interface MyRidesService {

    List<RideBasicInfoDTO> getUpcomingRides(String riderUserId, RideDTO requestContent);
}
