package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.*;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Get Car Types
 */
@CrossOrigin
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseListDTO<VehicleResponseDTO> getCarTypes(@PathVariable(required = true) String userId) {
        ResponseListDTO<VehicleResponseDTO> response = new ResponseListDTO<>();
        try {
            List<VehicleResponseDTO> resultList = vehicleService.getUserVehicles(userId);
            if (!resultList.isEmpty()) {
                response.setResponseContent(resultList);
                response.setSuccess(true);
                response.setErrorMessage(null);
            } else {
                response.setResponseContent(null);
                response.setSuccess(true);
                response.setErrorMessage("No Car Type Available");
            }
        } catch (Exception exp) {
            response.setResponseContent(null);
            response.setSuccess(false);
            response.setErrorMessage(exp.getMessage());
            exp.printStackTrace();
        }
        return response;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseDTO<Void> registerVehicle(@RequestBody(required = true) RequestDTO<VehicleRegisterRequestDTO> requestInfo) {
        ResponseDTO<Void> responseDTO = new ResponseDTO<>();
        try {
            if (requestInfo.getRequestContent() == null) {
                throw new Exception("Vehicle Details Required!!");
            }
            if (vehicleService.addNewVehicle(requestInfo.getUserId(), requestInfo.getRequestContent())) {
                responseDTO.setSuccess(true);
                responseDTO.setErrorMessage(null);
                responseDTO.setResponseContent(null);
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setErrorMessage(ErrorMessages.SAME_VEHICLE_REGISTRATION_ATTEMPT);
                responseDTO.setResponseContent(null);
            }
        } catch (Exception exp) {
            responseDTO.setSuccess(false);
            responseDTO.setResponseContent(null);
            responseDTO.setErrorMessage(exp.getMessage());
            exp.printStackTrace();
        }
        return responseDTO;
    }
}
