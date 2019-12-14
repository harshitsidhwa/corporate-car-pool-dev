package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.AvailableRideInfoDTO;
import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.service.FindRideService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindRideServiceImplementation implements FindRideService {

    public List<AvailableRideInfoDTO> getBestMatchingRide(RideDTO requestContent) {
        List<AvailableRideInfoDTO> result = new ArrayList<>();
        return result;
    }
}
