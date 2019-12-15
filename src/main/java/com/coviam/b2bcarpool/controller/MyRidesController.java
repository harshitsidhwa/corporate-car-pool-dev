package com.coviam.b2bcarpool.controller;

import java.util.List;

import com.coviam.b2bcarpool.dto.RequestDTO;
import com.coviam.b2bcarpool.dto.ResponseListDTO;
import com.coviam.b2bcarpool.dto.RideBasicInfoDTO;
import com.coviam.b2bcarpool.dto.RideDTO;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.MyRidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/myrides")
public class MyRidesController {

  @Autowired
  private MyRidesService myRidesService;

  @GetMapping("/upcoming")
  public ResponseListDTO<RideBasicInfoDTO> getUpcomingRides(
    @RequestBody(required = true) RequestDTO<RideDTO> upcomingRideReq) {

    ResponseListDTO<RideBasicInfoDTO> response = new ResponseListDTO<>();

    try {
      List<RideBasicInfoDTO> result = myRidesService
        .getUpcomingRides(upcomingRideReq.getUserId(), upcomingRideReq.getRequestContent());
      if (!result.isEmpty()) {
        response.setResponseContent(result);
        response.setSuccess(true);
        response.setErrorMessage(null);
      } else {
        response.setResponseContent(null);
        response.setSuccess(true);
        response.setErrorMessage(ErrorMessages.NO_DATA_AVAILABE);
      }
    } catch(Exception exp) {
      response.setResponseContent(null);
      response.setSuccess(false);
      response.setErrorMessage(exp.getMessage());
    }
    return response;
  }

  @GetMapping("/history")
  public String getHistoryRides() {
    return "Hello!";
  }
}
