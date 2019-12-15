package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.CreateTripResponseDTO;
import com.coviam.b2bcarpool.dto.OfferRideDTO;
import com.coviam.b2bcarpool.dto.RequestDTO;
import com.coviam.b2bcarpool.dto.ResponseDTO;
import com.coviam.b2bcarpool.service.OfferRideService;
import com.google.maps.errors.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public ResponseDTO<CreateTripResponseDTO> createTrip(@RequestBody(required = true) RequestDTO<OfferRideDTO> tripDetails) throws InterruptedException, ApiException, IOException {
        log.info("CreateTripRequest Params-->" + tripDetails.toString());
        ResponseDTO<CreateTripResponseDTO> responseDTO = new ResponseDTO<>();
        CreateTripResponseDTO createTripDTO = offerRideService.createTrip(tripDetails.getUserId(), tripDetails.getRequestContent());
        try {
            if (createTripDTO.isTripCreated()) {
                responseDTO.setSuccess(true);
                responseDTO.setErrorMessage(null);
                responseDTO.setResponseContent(createTripDTO);
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setErrorMessage(createTripDTO.getErrMsg());
                responseDTO.setResponseContent(null);
            }
        } catch (Exception exp) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(exp.getMessage());
            responseDTO.setResponseContent(null);
        }
        return responseDTO;
    }
}
