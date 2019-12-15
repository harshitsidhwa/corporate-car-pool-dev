package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.config.GoogleMapsConfig;
import com.coviam.b2bcarpool.dto.JoinRideResponseDTO;
import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.dto.TripBasicInfoDTO;
import com.coviam.b2bcarpool.helper.DateHelper;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.models.Riders;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.models.User;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.models.enums.TripStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.repository.TripsRepository;
import com.coviam.b2bcarpool.repository.UserRepository;
import com.coviam.b2bcarpool.service.FindRideService;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FindRideServiceImplementation implements FindRideService {

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private TripsRepository tripsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GoogleMapsConfig googleMapsConfig;

    /**
     * Allot user chosen ride to trip
     *
     * @param riderUserId
     * @param requestContent
     * @return
     */
    @Override
    public JoinRideResponseDTO insertRideToTrip(String riderUserId, RideDTO requestContent) throws InterruptedException, ApiException, IOException {
        JoinRideResponseDTO responseDTO = new JoinRideResponseDTO();
        if (rideRepository.findByUserIdAndAllottedTripId(riderUserId, requestContent.getTripId()) != null) {
            responseDTO.setRideJoined(false);
            responseDTO.setErrMsg(ErrorMessages.RIDER_ALREADY_JOINED);
            return responseDTO;
        }
        Trips trip = tripsRepository.findByTripId(requestContent.getTripId());
        if (trip.getTripStatus().equalsIgnoreCase(TripStatusEnum.COMPLETED_STATUS) || trip.getTripStatus().equalsIgnoreCase(TripStatusEnum.CANCELLED_STATUS)) {
            responseDTO.setRideJoined(false);
            responseDTO.setErrMsg(ErrorMessages.TRIP_ALREADY_ENDED);
            return responseDTO;
        }
        if (trip.getTripStatus().equalsIgnoreCase(TripStatusEnum.FILLED_STATUS) || trip.getCurrSeats() == trip.getOfferedSeats()) {
            responseDTO.setRideJoined(false);
            responseDTO.setErrMsg(ErrorMessages.NO_SEATS_AVAILABLE_IN_TRIP);
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
        Riders rider = new Riders();
        BeanUtils.copyProperties(requestContent, rider);
        rider.setUserId(riderUserId);
        rider.setAllottedTripId(requestContent.getTripId());
        rider.setRideStatus(RideStatusEnum.ALLOTTED_STATUS);
        rider.setCreatedBy(riderUserId);
        rideRepository.save(rider);
        rider = rideRepository.findByUserIdAndAllottedTripId(riderUserId, requestContent.getTripId());

        // Increase CurrSeats count
        // if all seats are fulled then change status
        trip.getJoinedRidersId().add(new ObjectId(rider.getRideId()));
        trip.setCurrSeats(trip.getCurrSeats() + 1);
        if (trip.getCurrSeats() == trip.getOfferedSeats()) {
            trip.setTripStatus(TripStatusEnum.FILLED_STATUS);
        }
        tripsRepository.save(trip);

        responseDTO.setRideJoined(true);
        responseDTO.setErrMsg(null);
        return responseDTO;
    }

    /**
     * Find the best suited trip options for the rider
     * first sort by smallest distance and the by time
     *
     * @param requestContent
     * @return
     */
    @Override
    public List<TripBasicInfoDTO> getBestMatchingRide(String userId, RideDTO requestContent) throws ParseException {
        List<TripBasicInfoDTO> result = new ArrayList<>();

        int initialGap = 2;
        Date dateTimeBeforeStartTime = DateHelper.getDateTimeBeforeHours(requestContent.getRideStartTime(), initialGap);
        Date dateTimeAfterStartTime = DateHelper.getDateTimeAfterHours(requestContent.getRideStartTime(), initialGap);
        log.info("TimeRangeRequested-->\n StartTime-->" + dateTimeBeforeStartTime + "\nEndTime-->" + dateTimeAfterStartTime);

        List<Trips> tripsFromDb = tripsRepository.findByTripStartTimeBetween(dateTimeBeforeStartTime, dateTimeAfterStartTime);
        log.info("FindTripsFromDB-->" + tripsFromDb.toString());

        for (Trips trips : tripsFromDb) {
            if (trips.getTripStatus().equalsIgnoreCase(TripStatusEnum.ACTIVE_STATUS) &&
                    ((trips.getOfferedSeats() - trips.getCurrSeats()) >= requestContent.getRequestedSeats())) {
                if (userId.equalsIgnoreCase(trips.getUserId())) continue;
                TripBasicInfoDTO singleTrip = new TripBasicInfoDTO();
                BeanUtils.copyProperties(trips, singleTrip);
                singleTrip.setNumberOfJoinedRiders(trips.getCurrSeats());
                User userInfo = userRepository.findByEmailId(trips.getUserId());
                singleTrip.setFullName(userInfo.getFullName());
                singleTrip.setPhoneNumber(userInfo.getPhoneNumber());
                result.add(singleTrip);
            }
        }
        return result.stream()
                .sorted(Comparator.comparing(TripBasicInfoDTO::getTripStartTime))
                .collect(Collectors.toList());
        // return result;
    }
}
