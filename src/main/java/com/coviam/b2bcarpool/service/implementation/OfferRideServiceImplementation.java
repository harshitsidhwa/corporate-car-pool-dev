package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.config.GoogleMapsConfig;
import com.coviam.b2bcarpool.dto.CreateTripResponseDTO;
import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.coviam.b2bcarpool.models.Riders;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.models.enums.TripStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.repository.TripsRepository;
import com.coviam.b2bcarpool.service.OfferRideService;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
    private GoogleMapsConfig googleMapsConfig;

    @Override
    public CreateTripResponseDTO createTrip(String userId, OfferRideDTO requestContent) throws InterruptedException, ApiException, IOException {
        if (tripsRepository.findByUserIdAndTripStartTime(userId, requestContent.getTripStartTime()) != null) {
            CreateTripResponseDTO responseDTO = new CreateTripResponseDTO();
            responseDTO.setTripCreated(false);
            responseDTO.setErrMsg("A Trip Already Exists for selected Time");
            return responseDTO;
        }
        GeocodingResult[] results;
        if (requestContent.getPickupPoint().getPlaceAddress().isEmpty() || requestContent.getPickupPoint().getPlaceAddress() == null) {
            LatLng pickupAddressLatLng = new LatLng(requestContent.getPickupPoint().getLatitude(), requestContent.getPickupPoint().getLongitude());
            results = GeocodingApi.reverseGeocode(googleMapsConfig.getContext(), pickupAddressLatLng).await();
            // Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (results.length > 0) {
                requestContent.getPickupPoint().setPlaceAddress(results[0].formattedAddress);
            }
        }

        if (requestContent.getDestinationPoint().getPlaceAddress().isEmpty() || requestContent.getDestinationPoint().getPlaceAddress() == null) {
            LatLng destinationAddressLatLng = new LatLng(requestContent.getDestinationPoint().getLatitude(), requestContent.getDestinationPoint().getLongitude());
            results = GeocodingApi.reverseGeocode(googleMapsConfig.getContext(), destinationAddressLatLng).await();
            if (results.length > 0) {
                requestContent.getDestinationPoint().setPlaceAddress(results[0].formattedAddress);
            }
        }
        Trips newTrip = new Trips();
        BeanUtils.copyProperties(requestContent, newTrip);
        newTrip.setUserId(userId);
        newTrip.setTripStatus(TripStatusEnum.ACTIVE_STATUS);
        newTrip.setCreatedDate(new Date());
        newTrip.setCreatedBy(userId);
        newTrip.setCurrSeats(0);
        log.info("CreateTripRequest-->" + newTrip.toString());
        tripsRepository.save(newTrip);
        Trips trip = tripsRepository.findByUserIdAndTripStartTime(userId, requestContent.getTripStartTime());

        Riders rider = new Riders();
        BeanUtils.copyProperties(trip, rider);
        rider.setAllottedTripId(trip.getTripId());
        rider.setRequestedSeats(0);
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
