package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.RequestDTO;
import com.coviam.b2bcarpool.dto.ResponseListDTO;
import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.MyRidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/myrides")
public class MyRidesController {

    @Autowired
    private MyRidesService myRidesService;

    @PostMapping(value = "/upcoming", consumes = "application/json", produces = "application/json")
    public ResponseListDTO<RideBasicInfoDTO> getUpcomingRides(
            @RequestBody(required = true) RequestDTO<Void> upcomingRideReq) {

        ResponseListDTO<RideBasicInfoDTO> response = new ResponseListDTO<>();

        try {
            List<RideBasicInfoDTO> result = myRidesService
                    .getUpcomingRides(upcomingRideReq.getUserId());
            if (!result.isEmpty()) {
                response.setResponseContent(result);
                response.setSuccess(true);
                response.setErrorMessage(null);
            } else {
                response.setResponseContent(null);
                response.setSuccess(true);
                response.setErrorMessage(ErrorMessages.NO_DATA_AVAILABE);
            }
        } catch (Exception exp) {
            response.setResponseContent(null);
            response.setSuccess(false);
            response.setErrorMessage(exp.getMessage());
            exp.printStackTrace();
        }
        return response;
    }

    @PostMapping(value = "/history", consumes = "application/json", produces = "application/json")
    public ResponseListDTO<RideBasicInfoDTO> getHistoryRides(
            @RequestBody(required = true) RequestDTO<Void> historyRideReq) {

        ResponseListDTO<RideBasicInfoDTO> response = new ResponseListDTO<>();

        try {
            List<RideBasicInfoDTO> result = myRidesService
                    .getHistoryRides(historyRideReq.getUserId());
            if (!result.isEmpty()) {
                response.setResponseContent(result);
                response.setSuccess(true);
                response.setErrorMessage(null);
            } else {
                response.setResponseContent(null);
                response.setSuccess(true);
                response.setErrorMessage(ErrorMessages.NO_DATA_AVAILABE);
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
