package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.CreateTripResponseDTO;
import com.coviam.b2bcarpool.dto.OfferRideDTO;

public interface OfferRideService {
    CreateTripResponseDTO createTrip(String userId, OfferRideDTO requestContent);
}
