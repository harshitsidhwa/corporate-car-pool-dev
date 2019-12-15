package com.coviam.b2bcarpool.dto;

import com.coviam.b2bcarpool.models.Cars;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDTO {
    private String fullName;
    private String emailId;
    private String userId = emailId;
    private String phoneNumber;
    private String password;
    private int age;
    private Date dob;
    private String empId;
    private String organisationName;
    private String profilePicUrl;
    private List<Cars> userCars;
}
