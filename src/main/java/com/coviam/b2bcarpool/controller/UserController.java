package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.RequestDTO;
import com.coviam.b2bcarpool.dto.ResponseDTO;
import com.coviam.b2bcarpool.dto.UserInfoDTO;
import com.coviam.b2bcarpool.helper.ErrorMessages;
import com.coviam.b2bcarpool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Get User Profile
 * Get User Basic Profile
 * Register User Cars
 * Get User Cars
 */
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get User Info
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/{userId}")
    public ResponseDTO<UserInfoDTO> getUserInfo(@PathVariable(required = true) String userId) {
        ResponseDTO<UserInfoDTO> responseDTO = new ResponseDTO<>();
        try {
            responseDTO.setResponse(userService.getUserInfo(userId));
            if (responseDTO.getResponse() != null) {
                responseDTO.setSuccess(true);
                responseDTO.setErrorMessage(null);
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setErrorMessage(ErrorMessages.USER_NOT_EXISTS);
            }
        } catch (Exception e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Add New User
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseDTO<Void> addNewUser(@RequestBody(required = true) RequestDTO<UserInfoDTO> requestDTO) {
        ResponseDTO<Void> responseDTO = new ResponseDTO<>();
        try {
            if (userService.registerNewUser(requestDTO.getRequestContent())) {
                responseDTO.setErrorMessage(null);
                responseDTO.setSuccess(true);
                responseDTO.setResponse(null);
            } else {
                responseDTO.setErrorMessage(ErrorMessages.SOME_UNEXPECTED_ERROR_OCCUR);
                responseDTO.setSuccess(false);
                responseDTO.setResponse(null);
            }
        } catch (Exception exp) {
            responseDTO.setErrorMessage(exp.getMessage());
            responseDTO.setSuccess(false);
            responseDTO.setResponse(null);
        }
        return responseDTO;
    }

    private boolean verifyUser(String userId) {
        return true;
    }
}
