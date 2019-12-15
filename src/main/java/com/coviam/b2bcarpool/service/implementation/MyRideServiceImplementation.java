package com.coviam.b2bcarpool.service.implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;
import com.coviam.b2bcarpool.models.Riders;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.repository.TripsRepository;
import com.coviam.b2bcarpool.service.MyRidesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyRideServiceImplementation implements MyRidesService {

    @Autowired
    private TripsRepository tripsRepository;

    @Autowired
    private RideRepository rideRepository;

    @Override
    public List<RideBasicInfoDTO> getUpcomingRides(String riderUserId) {

        List<RideBasicInfoDTO> result = new ArrayList<>();
        // List<Trips> trips = tripsRepository.findAllByUserIdAndTripStatus(riderUserId, TripStatusEnum.ACTIVE_STATUS);
        List<Riders> rides = rideRepository.findByUserIdAndRideStatus(riderUserId, RideStatusEnum.ALLOTTED_STATUS);

        // log.info("UpcomingRides--> " + rides.toString());

        for (Riders ride : rides) {
            RideBasicInfoDTO singleRide = new RideBasicInfoDTO();
            BeanUtils.copyProperties(ride, singleRide);
            singleRide.setTripId(ride.getAllottedTripId());
            singleRide.setTripStatus(ride.getRideStatus());
            result.add(singleRide);
        }

        return result.stream()
                .sorted(Comparator.comparing(RideBasicInfoDTO::getRideStartTime))
                .collect(Collectors.toList());
    }

    public List<RideBasicInfoDTO> getHistoryRides(String riderUserId) {

        List<RideBasicInfoDTO> result = new ArrayList<>();
        //List<Trips> trips = tripsRepository.findAllByUserIdAndTripStatus(riderUserId, TripStatusEnum
        // .COMPLETED_STATUS);
        List<Riders> rides = rideRepository.findAllByUserIdAndRideStatus(riderUserId, RideStatusEnum.COMPLETED_STATUS);

        for (Riders ride : rides) {
            RideBasicInfoDTO singleRide = new RideBasicInfoDTO();
            BeanUtils.copyProperties(ride, singleRide);
            singleRide.setTripId(ride.getAllottedTripId());
            singleRide.setTripStatus(ride.getRideStatus());
            result.add(singleRide);
        }

        return result.stream()
          .sorted(Comparator.comparing(RideBasicInfoDTO::getRideStartTime))
          .collect(Collectors.toList());
    }
}
