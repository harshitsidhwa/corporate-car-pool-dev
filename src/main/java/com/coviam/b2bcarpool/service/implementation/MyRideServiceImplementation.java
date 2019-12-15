package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;
import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.models.Riders;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.models.enums.TripStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.repository.TripsRepository;
import com.coviam.b2bcarpool.service.MyRidesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyRideServiceImplementation implements MyRidesService {

    @Autowired
    private TripsRepository tripsRepository;

    @Autowired
    private RideRepository rideRepository;

    @Override
    public List<RideBasicInfoDTO> getUpcomingRides(String riderUserId, RideDTO requestContent) {

        List<RideBasicInfoDTO> result = new ArrayList<>();
        List<Trips> trips = tripsRepository.findAllByUserIdAndTripStatus(riderUserId, TripStatusEnum.ACTIVE_STATUS);
        List<Riders> rides = rideRepository.findAllByUserIdAndRideStatus(riderUserId, RideStatusEnum.ALLOTTED_STATUS);

        for (Trips trip : trips) {
            RideBasicInfoDTO singleRide = new RideBasicInfoDTO();
            BeanUtils.copyProperties(trip, singleRide);
            result.add(singleRide);
        }

        for (Riders ride : rides) {
            RideBasicInfoDTO singleRide = new RideBasicInfoDTO();
            BeanUtils.copyProperties(ride, singleRide);
            result.add(singleRide);
        }

        return result.stream()
                .sorted(Comparator.comparing(RideBasicInfoDTO::getRideStartTime))
                .collect(Collectors.toList());
    }

}
