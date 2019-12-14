package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.CarTypeDTO;
import com.coviam.b2bcarpool.dto.ResponseListDTO;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Get Car Types
 */
@CrossOrigin
@RestController
public class CarTypeController {

    @Autowired
    private CarTypeService carTypeService;

    @RequestMapping(value = "/api/cars/types", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseListDTO<CarTypeDTO> getCarTypes() {
        ResponseListDTO<CarTypeDTO> response = new ResponseListDTO<>();
        try {
            List<CarTypeDTO> resultList = carTypeService.getVehicleTypes();
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
            response.setErrorMessage(ErrorMessages.SOME_UNEXPECTED_ERROR_OCCUR);
        }
        return response;
    }

}
