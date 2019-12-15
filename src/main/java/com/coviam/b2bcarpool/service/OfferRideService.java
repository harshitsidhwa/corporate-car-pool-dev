package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.CreateTripResponseDTO;
import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface OfferRideService {
    CreateTripResponseDTO createTrip(String userId, OfferRideDTO requestContent) throws InterruptedException, ApiException, IOException;
}
