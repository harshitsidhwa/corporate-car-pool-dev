package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.models.enums.TripStatusEnum;
import com.coviam.b2bcarpool.repository.OfferRideRepository;
import com.coviam.b2bcarpool.service.OfferRideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class OfferRideServiceImplementation implements OfferRideService {

    @Autowired
    private OfferRideRepository offerRideRepository;

    @Override
    public boolean createTrip(String userId, OfferRideDTO requestContent) {
        Trips newTrip = new Trips();
        newTrip.setUserId(userId);
        newTrip.setCreatedDate(new Date());
        newTrip.setCreatedBy(userId);
        newTrip.setPickupPoint(requestContent.getPickupPoint());
        newTrip.setDestinationPoint(requestContent.getDestinationPoint());
        newTrip.setOfferedSeats(requestContent.getOfferedSeats());
        newTrip.setTripStartTime(requestContent.getTripStartTime());
        newTrip.setTripStatus(TripStatusEnum.ACTIVE_STATUS);
        log.info("CreateTripRequest-->" + newTrip.toString());
        offerRideRepository.save(newTrip);
        return true;
    }
}
