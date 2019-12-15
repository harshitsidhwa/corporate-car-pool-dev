package com.coviam.b2bcarpool.controller;

import com.coviam.b2bcarpool.dto.*;
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
            responseDTO.setResponseContent(userService.getUserInfo(userId));
            if (responseDTO.getResponseContent() != null) {
                responseDTO.setSuccess(true);
                responseDTO.setErrorMessage(null);
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setErrorMessage(ErrorMessages.USER_NOT_EXISTS);
            }
        } catch (Exception e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(e.getMessage());
            e.printStackTrace();
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
    public ResponseDTO<SignUpResponseDTO> addNewUser(@RequestBody(required = true) RequestDTO<UserInfoDTO> requestDTO) {
        ResponseDTO<SignUpResponseDTO> responseDTO = new ResponseDTO<>();
        try {
            SignUpResponseDTO signUpResponse = userService.registerNewUser(requestDTO.getRequestContent());
            if (signUpResponse.isSignUpSuccess()) {
                responseDTO.setErrorMessage(null);
                responseDTO.setSuccess(true);
                responseDTO.setResponseContent(signUpResponse);
            } else {
                responseDTO.setErrorMessage(ErrorMessages.SOME_UNEXPECTED_ERROR_OCCUR);
                responseDTO.setSuccess(false);
                responseDTO.setResponseContent(null);
            }
        } catch (Exception exp) {
            responseDTO.setErrorMessage(exp.getMessage());
            responseDTO.setSuccess(false);
            responseDTO.setResponseContent(null);
            exp.printStackTrace();
        }
        return responseDTO;
    }

    /**
     * Check User Login
     *
     * @param loginInfo
     * @return
     */
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseDTO<LoginResponseDTO> loginUser(@RequestBody(required = true) RequestDTO<LoginRequestDTO> loginInfo) {
        ResponseDTO<LoginResponseDTO> loginResponse = new ResponseDTO<>();
        try {
            LoginResponseDTO responseContent = userService.checkUserLogin(loginInfo.getRequestContent());
            if (responseContent.isLoginSuccess()) {
                loginResponse.setErrorMessage(null);
                loginResponse.setSuccess(true);
                loginResponse.setResponseContent(responseContent);
            } else {
                if (responseContent.getErrMsg().contains("Password")) {
                    loginResponse.setErrorMessage(ErrorMessages.WRONG_PASSWORD);
                    loginResponse.setSuccess(false);
                    loginResponse.setResponseContent(null);
                } else if (responseContent.getErrMsg().contains("EXISTS")) {
                    loginResponse.setErrorMessage(ErrorMessages.USER_NOT_EXISTS);
                    loginResponse.setSuccess(false);
                    loginResponse.setResponseContent(null);
                }
            }
        } catch (Exception exp) {
            loginResponse.setErrorMessage(exp.getMessage());
            loginResponse.setSuccess(false);
            loginResponse.setResponseContent(null);
            exp.printStackTrace();
        }
        return loginResponse;
    }

    private boolean verifyUser(String userId) {
        return true;
    }
}
