package com.coviam.b2bcarpool.service;

import java.util.List;

import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;
import com.coviam.b2bcarpool.dto.RideDTO;

public interface MyRidesService {
  //List<RideBasicInfoDTO> ();

  List<RideBasicInfoDTO> getUpcomingRides(String riderUserId, RideDTO requestContent);
}
