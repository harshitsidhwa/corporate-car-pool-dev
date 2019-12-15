package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.LoginRequestDTO;
import com.coviam.b2bcarpool.dto.LoginResponseDTO;
import com.coviam.b2bcarpool.dto.SignUpResponseDTO;
import com.coviam.b2bcarpool.dto.UserInfoDTO;

import java.util.concurrent.ExecutionException;

public interface UserService {

    UserInfoDTO getUserInfo(String userId) throws ExecutionException, InterruptedException;

    SignUpResponseDTO registerNewUser(UserInfoDTO userInfo);

    LoginResponseDTO checkUserLogin(LoginRequestDTO requestContent);
}
