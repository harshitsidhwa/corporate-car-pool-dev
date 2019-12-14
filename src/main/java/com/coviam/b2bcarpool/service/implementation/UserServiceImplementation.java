package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.UserInfoDTO;
import com.coviam.b2bcarpool.helper.DbHelper;
import com.coviam.b2bcarpool.repository.UserRepository;
import com.coviam.b2bcarpool.service.UserService;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DbHelper dbHelper;

    @Override
    public boolean registerNewUser(UserInfoDTO userInfo) {
        dbHelper.getFirestoreDb().collection("users").add(userInfo);
        return true;
    }

    @Override
    public UserInfoDTO getUserInfo(String userId) throws ExecutionException, InterruptedException {
        QuerySnapshot res = dbHelper.getFirestoreDb().collection("users").whereEqualTo("userId", userId).get().get();
        UserInfoDTO userInfoDTO = null;
        if (res.isEmpty()) return null;
        userInfoDTO = res.getDocuments().get(0).toObject(UserInfoDTO.class);
        return userInfoDTO;
    }
}
