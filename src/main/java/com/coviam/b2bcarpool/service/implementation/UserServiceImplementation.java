package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.LoginRequestDTO;
import com.coviam.b2bcarpool.dto.LoginResponseDTO;
import com.coviam.b2bcarpool.dto.SignUpResponseDTO;
import com.coviam.b2bcarpool.dto.UserInfoDTO;
import com.coviam.b2bcarpool.models.User;
import com.coviam.b2bcarpool.repository.UserRepository;
import com.coviam.b2bcarpool.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public SignUpResponseDTO registerNewUser(UserInfoDTO userInfo) {
        SignUpResponseDTO responseDTO = new SignUpResponseDTO();
        User newUser = new User();
        BeanUtils.copyProperties(userInfo, newUser);
        newUser.setPassword(userInfo.getPassword());
        newUser.setUserId(userInfo.getEmailId().toLowerCase());
        log.info("RegisterNewUser-->" + newUser);
        userRepository.save(newUser);
        responseDTO.setSignUpSuccess(true);
        responseDTO.setUserId(userRepository.findByEmailId(userInfo.getEmailId()).getUserId());
        return responseDTO;
    }

    @Override
    public UserInfoDTO getUserInfo(String userId) throws ExecutionException, InterruptedException {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        User foundUser = userRepository.findByUserId(userId);
        BeanUtils.copyProperties(foundUser, userInfoDTO);
        return userInfoDTO;
    }

    @Override
    public LoginResponseDTO checkUserLogin(LoginRequestDTO requestContent) {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        User userData = userRepository.findByEmailId(requestContent.getEmailId().toLowerCase());
        if (userData != null) {
            if (userData.getPassword().equals(requestContent.getPassword())) {
                responseDTO.setUserId(userData.getUserId());
                responseDTO.setUsername(userData.getFullName());
                responseDTO.setLoginSuccess(true);
                responseDTO.setErrMsg(null);
            } else {
                responseDTO.setUserId(null);
                responseDTO.setLoginSuccess(false);
                responseDTO.setErrMsg("Password_Not_Match");
            }
        } else {
            responseDTO.setUserId(null);
            responseDTO.setLoginSuccess(false);
            responseDTO.setErrMsg("USER_NOT_EXISTS");
        }
        return responseDTO;
    }
}
