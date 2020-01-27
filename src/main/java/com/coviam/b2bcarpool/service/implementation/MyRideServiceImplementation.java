package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;
import com.coviam.b2bcarpool.models.Riders;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.repository.TripsRepository;
import com.coviam.b2bcarpool.service.MyRidesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Riders> rides = rideRepository.findByUserIdAndRideStatus(riderUserId, RideStatusEnum.ALLOTTED_STATUS);
        log.info("UpcomingRide For " + riderUserId + " \n--> " + rides);

        for (Riders ride : rides) {
            RideBasicInfoDTO singleRide = new RideBasicInfoDTO();
            BeanUtils.copyProperties(ride, singleRide);
            singleRide.setTripId(ride.getAllottedTripId());
            singleRide.setTripStatus(ride.getRideStatus());
            Trips trip = tripsRepository.findByTripId(ride.getAllottedTripId());
            if (trip == null) continue;
            singleRide.setVehicleNumber(trip.getVehicleNumber());
            if (ride.isRiderTripOwner()) {
                singleRide.setSeats("Offered: " + trip.getOfferedSeats());
            } else {
                singleRide.setSeats("Requested: " + ride.getRequestedSeats());
            }
            result.add(singleRide);
        }

        return result.stream()
                .sorted(Comparator.comparing(RideBasicInfoDTO::getRideStartTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<RideBasicInfoDTO> getHistoryRides(String riderUserId) {

        List<RideBasicInfoDTO> result = new ArrayList<>();

        List<String> rideStatus = new ArrayList<String>() {
            {
                add(RideStatusEnum.COMPLETED_STATUS);
                add(RideStatusEnum.CANCELLED_STATUS);
            }
        };
        List<Riders> rides = rideRepository.findAllByUserIdAndRideStatusIn(riderUserId, rideStatus);
        log.info("HistoryRide For " + riderUserId + " \n--> " + rides);
        for (Riders ride : rides) {
            RideBasicInfoDTO singleRide = new RideBasicInfoDTO();
            BeanUtils.copyProperties(ride, singleRide);
            singleRide.setTripId(ride.getAllottedTripId());
            singleRide.setTripStatus(ride.getRideStatus());
            Trips trip = tripsRepository.findByTripId(ride.getAllottedTripId());
            if (trip == null) continue;
            singleRide.setVehicleNumber(trip.getVehicleNumber());
            if (ride.isRiderTripOwner()) {
                singleRide.setSeats("Offered: " + trip.getOfferedSeats());
            } else {
                singleRide.setSeats("Requested: " + ride.getRequestedSeats());
            }
            result.add(singleRide);
        }

        return result.stream()
                .sorted(Comparator.comparing(RideBasicInfoDTO::getRideStartTime))
                .collect(Collectors.toList());
    }
}
