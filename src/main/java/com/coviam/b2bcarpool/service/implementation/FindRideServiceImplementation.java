package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.dto.TripBasicInfoDTO;
import com.coviam.b2bcarpool.helper.DateHelper;
import com.coviam.b2bcarpool.models.Riders;
import com.coviam.b2bcarpool.models.Trips;
import com.coviam.b2bcarpool.models.enums.RideStatusEnum;
import com.coviam.b2bcarpool.models.enums.TripStatusEnum;
import com.coviam.b2bcarpool.repository.RideRepository;
import com.coviam.b2bcarpool.repository.TripsRepository;
import com.coviam.b2bcarpool.service.FindRideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * Allot user chosen ride to trip
     *
     * @param riderUserId
     * @param requestContent
     * @return
     */
    public boolean insertRideToTrip(String riderUserId, RideDTO requestContent) {
        Riders tripRider = new Riders();
        BeanUtils.copyProperties(requestContent, tripRider);
        tripRider.setUserId(riderUserId);
        tripRider.setAllottedTripId(requestContent.getTripId());
        tripRider.setRideStatus(RideStatusEnum.ALLOTTED_STATUS);
        tripRider.setCreatedBy(riderUserId);
        rideRepository.save(tripRider);
        return true;
    }

    /**
     * Find the best suited trip options for the rider
     * first sort by smallest distance and the by time
     *
     * @param requestContent
     * @return
     */
    public List<TripBasicInfoDTO> getBestMatchingRide(RideDTO requestContent) throws ParseException {
        List<TripBasicInfoDTO> result = new ArrayList<>();
        int initialGap = 2;
        Date dateTimeBeforeStartTime = DateHelper.getDateTimeBeforeHours(requestContent.getRideStartTime(), initialGap);
        Date dateTimeAfterStartTime = DateHelper.getDateTimeAfterHours(requestContent.getRideStartTime(), initialGap);
        log.info("TimeRangeRequested-->\n StartTime-->" + dateTimeBeforeStartTime + "\nEndTime-->" + dateTimeAfterStartTime);
        List<Trips> tripsFromDb = tripsRepository.findByTripStartTimeBetween(dateTimeBeforeStartTime, dateTimeAfterStartTime);
        log.info("FindTripsFromDB-->" + tripsFromDb.toString());
        for (Trips trips : tripsFromDb) {
            if (trips.getTripStatus().equalsIgnoreCase(TripStatusEnum.ACTIVE_STATUS) && trips.getCurrSeats() > requestContent.getRequestedSeats()) {
                TripBasicInfoDTO singleTrip = new TripBasicInfoDTO();
                BeanUtils.copyProperties(trips, singleTrip);
                singleTrip.setNumberOfJoinedRiders(trips.getCurrSeats());
                result.add(singleTrip);
            }
        }
        return result.stream()
                .sorted(Comparator.comparing(TripBasicInfoDTO::getTripStartTime))
                .collect(Collectors.toList());
        // return result;
    }
}
