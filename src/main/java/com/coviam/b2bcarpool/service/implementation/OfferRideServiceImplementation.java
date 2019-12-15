package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.CreateTripResponseDTO;
import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.coviam.b2bcarpool.models.Riders;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.models.enums.TripStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.repository.TripsRepository;
import com.coviam.b2bcarpool.service.OfferRideService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OfferRideServiceImplementation implements OfferRideService {

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private TripsRepository tripsRepository;

    @Override
    public CreateTripResponseDTO createTrip(String userId, OfferRideDTO requestContent) {
        if (tripsRepository.findByUserIdAndTripStartTime(userId, requestContent.getTripStartTime()) != null) {
            CreateTripResponseDTO responseDTO = new CreateTripResponseDTO();
            responseDTO.setTripCreated(false);
            responseDTO.setErrMsg("A Trip Already Exists for selected Time");
            return responseDTO;
        }
        Trips newTrip = new Trips();
        newTrip.setUserId(userId);
        newTrip.setPickupPoint(requestContent.getPickupPoint());
        newTrip.setDestinationPoint(requestContent.getDestinationPoint());
        newTrip.setOfferedSeats(requestContent.getOfferedSeats());
        newTrip.setTripStartTime(requestContent.getTripStartTime());
        newTrip.setTripStatus(TripStatusEnum.ACTIVE_STATUS);
        newTrip.setVehicleNumber(requestContent.getVehicleNumber());
        newTrip.setCreatedDate(new Date());
        newTrip.setCreatedBy(userId);
        log.info("CreateTripRequest-->" + newTrip.toString());
        tripsRepository.save(newTrip);
        Trips trip = tripsRepository.findByUserIdAndTripStartTime(userId, requestContent.getTripStartTime());

        Riders rider = new Riders();
        BeanUtils.copyProperties(trip, rider);
        rider.setAllottedTripId(trip.getTripId());
        rider.setRideStatus(RideStatusEnum.ALLOTTED_STATUS);
        rider.setRideStartTime(trip.getTripStartTime());
        rider.setRiderTripOwner(true);
        rideRepository.save(rider);
        Riders currRide = rideRepository.findByUserIdAndAllottedTripId(userId, trip.getTripId());

        List<ObjectId> tripRiders = new ArrayList<>();
        tripRiders.add(new ObjectId(currRide.getRideId()));
        trip.setJoinedRidersId(tripRiders);
        tripsRepository.save(trip);

        CreateTripResponseDTO responseDTO = new CreateTripResponseDTO();
        BeanUtils.copyProperties(newTrip, responseDTO);
        responseDTO.setTripCreated(true);
        return responseDTO;
    }
}
