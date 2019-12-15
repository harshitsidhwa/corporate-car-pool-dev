package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.JoinRideResponseDTO;
import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.dto.TripBasicInfoDTO;

import java.text.ParseException;
import java.util.List;

public interface FindRideService {

    List<TripBasicInfoDTO> getBestMatchingRide(RideDTO requestContent) throws ParseException;

    JoinRideResponseDTO insertRideToTrip(String riderUserId, RideDTO requestContent);
}
