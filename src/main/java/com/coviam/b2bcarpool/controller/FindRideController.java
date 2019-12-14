package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.RequestDTO;
import com.coviam.b2bcarpool.dto.ResponseDTO;
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
    public ResponseListDTO<RideDTO> findRides(@RequestBody(required = true) RequestDTO<RideDTO> rideReq) {
        ResponseListDTO<RideDTO> response = new ResponseListDTO<>();
        try {
            List<RideDTO> availableRideInfoList = findRideService.getBestMatchingRide(rideReq.getRequestContent());
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
            response.setErrorMessage(exp.getMessage());
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
    public ResponseDTO<RideDTO> joinATrip(@RequestBody(required = true) RequestDTO<RideDTO> tripJoinReq) {
        ResponseDTO<RideDTO> response = new ResponseDTO<>();
        try {
            if (findRideService.insertRideToTrip(tripJoinReq.getUserId(), tripJoinReq.getRequestContent())) {
                response.setResponseContent(tripJoinReq.getRequestContent());
                response.setSuccess(true);
                response.setErrorMessage(null);
            } else {
                response.setResponseContent(null);
                response.setSuccess(true);
                response.setErrorMessage(ErrorMessages.SOME_UNEXPECTED_ERROR_OCCUR);
            }
        } catch (Exception exp) {
            response.setResponseContent(null);
            response.setSuccess(false);
            response.setErrorMessage(exp.getMessage());
        }
        return response;
    }
}
