package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.*;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.FindRideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseListDTO<TripBasicInfoDTO> findRides(@RequestBody(required = true) RequestDTO<RideDTO> rideReq) {
        log.info("FindRideReq--> " + rideReq);
        ResponseListDTO<TripBasicInfoDTO> response = new ResponseListDTO<>();
        try {
            List<TripBasicInfoDTO> availableRideInfoList = findRideService.getBestMatchingRide(rideReq.getUserId(), rideReq.getRequestContent());
            if (!availableRideInfoList.isEmpty()) {
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
            response.setErrorMessage(exp.getMessage());
            exp.printStackTrace();
        }
        return response;
    }

    /**
     * Join a Trip
     *
     * @param tripJoinReq
     * @return
     */
    @PutMapping(value = "/join-trip", consumes = "application/json", produces = "application/json")
    public ResponseDTO<JoinRideResponseDTO> joinATrip(@RequestBody(required = true) RequestDTO<RideDTO> tripJoinReq) {
        log.info("JoinRideReq--> " + tripJoinReq);
        ResponseDTO<JoinRideResponseDTO> response = new ResponseDTO<>();
        try {
            JoinRideResponseDTO joinResponse = findRideService.insertRideToTrip(tripJoinReq.getUserId(), tripJoinReq.getRequestContent());
            if (joinResponse.isRideJoined()) {
                response.setResponseContent(joinResponse);
                response.setSuccess(true);
                response.setErrorMessage(null);
            } else {
                response.setResponseContent(null);
                response.setSuccess(false);
                response.setErrorMessage(joinResponse.getErrMsg());
            }
        } catch (Exception exp) {
            response.setResponseContent(null);
            response.setSuccess(false);
            response.setErrorMessage(exp.getMessage());
            exp.printStackTrace();
        }
        return response;
    }
}
