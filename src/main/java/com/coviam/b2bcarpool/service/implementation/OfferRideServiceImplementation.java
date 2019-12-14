package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.coviam.b2bcarpool.service.OfferRideService;
import org.springframework.stereotype.Service;

@Service
public class OfferRideServiceImplementation implements OfferRideService {

    @Override
    public boolean createTrip(String userId, OfferRideDTO requestContent){
        return true;
    }
}
