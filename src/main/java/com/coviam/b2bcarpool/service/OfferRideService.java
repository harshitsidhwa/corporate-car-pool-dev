package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.OfferRideDTO;

public interface OfferRideService {
    boolean createTrip(String userId, OfferRideDTO requestContent);
}
