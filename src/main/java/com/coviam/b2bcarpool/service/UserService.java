package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.UserInfoDTO;

import java.util.concurrent.ExecutionException;

public interface UserService {

    UserInfoDTO getUserInfo(String userId) throws ExecutionException, InterruptedException;

    boolean registerNewUser(UserInfoDTO userInfo);
}
