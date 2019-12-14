package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.repository.OfferRideRepository;
import com.coviam.b2bcarpool.service.OfferRideService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferRideServiceImplementation implements OfferRideService {

    @Autowired
    private OfferRideRepository offerRideRepository;

    @Override
    public boolean createTrip(String userId, OfferRideDTO requestContent) {
        Trips newTrip = new Trips();
        newTrip.setUserId(userId);
        BeanUtils.copyProperties(requestContent, newTrip);
        offerRideRepository.save(newTrip);
        return true;
    }
}
