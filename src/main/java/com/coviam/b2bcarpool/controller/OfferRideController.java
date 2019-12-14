package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.coviam.b2bcarpool.dto.RequestDTO;
import com.coviam.b2bcarpool.dto.ResponseDTO;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.OfferRideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/ride")
public class OfferRideController {

    @Autowired
    private OfferRideService offerRideService;

    /**
     * Create Trip
     *
     * @param tripDetails
     * @return
     */
    @PostMapping(value = "/create-trip", consumes = "application/json", produces = "application/json")
    public ResponseDTO<Void> createTrip(@RequestBody(required = true) RequestDTO<OfferRideDTO> tripDetails) {
        log.info("CreateTripRequest Params-->" + tripDetails.toString());
        ResponseDTO<Void> responseDTO = new ResponseDTO<>();
        if (offerRideService.createTrip(tripDetails.getUserId(), tripDetails.getRequestContent())) {
            responseDTO.setSuccess(true);
            responseDTO.setErrorMessage(null);
        } else {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(ErrorMessages.SOME_UNEXPECTED_ERROR_OCCUR);
        }
        responseDTO.setResponseContent(null);
        return responseDTO;
    }
}
