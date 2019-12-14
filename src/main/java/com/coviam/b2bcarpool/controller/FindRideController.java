package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.AvailableRideInfoDTO;
import com.coviam.b2bcarpool.dto.RequestDTO;
import com.coviam.b2bcarpool.dto.ResponseListDTO;
import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.FindRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rides")
public class FindRideController {

    @Autowired
    private FindRideService findRideService;

    /**
     * Find Rides
     *
     * @param rideReq
     * @return
     */
    @PostMapping(value = "/find-ride", consumes = "application/json", produces = "application/json")
    public ResponseListDTO<AvailableRideInfoDTO> findRides(@RequestBody(required = true) RequestDTO<RideDTO> rideReq) {
        ResponseListDTO<AvailableRideInfoDTO> response = new ResponseListDTO<>();
        try {
            List<AvailableRideInfoDTO> availableRideInfoList = findRideService.getBestMatchingRide(rideReq.getRequestContent());
            if (availableRideInfoList.size() > 0) {
                response.setResponseContent(availableRideInfoList);
                response.setSuccess(true);
                response.setErrorMessage(null);
            } else {
                response.setResponseContent(null);
                response.setSuccess(true);
                response.setErrorMessage(ErrorMessages.NO_TRIPS_FOUND);
            }
        } catch (Exception exp) {
            response.setResponseContent(null);
            response.setSuccess(false);
            response.setErrorMessage(ErrorMessages.SOME_UNEXPECTED_ERROR_OCCUR);
        }
        return response;
    }
}
