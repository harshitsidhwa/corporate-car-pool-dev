package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.models.TripRiders;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.service.FindRideService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindRideServiceImplementation implements FindRideService {

    @Autowired
    private RideRepository rideRepository;

    public List<RideDTO> getBestMatchingRide(RideDTO requestContent) {
        List<RideDTO> result = new ArrayList<>();
        return result;
    }

    public boolean insertRideToTrip(String riderUserId, RideDTO requestContent) {
        TripRiders tripRider = new TripRiders();
        BeanUtils.copyProperties(requestContent, tripRider);
        tripRider.setUserId(riderUserId);
        tripRider.setAllottedTripId(requestContent.getTripId());
        tripRider.setRideStatus(RideStatusEnum.ALLOTTED_STATUS);
        tripRider.setCreatedBy(riderUserId);
        rideRepository.save(tripRider);
        return true;
    }
}
