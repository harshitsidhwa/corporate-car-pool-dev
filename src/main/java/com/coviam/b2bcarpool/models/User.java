package com.coviam.b2bcarpool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class User {

    @Id
    private String userId;
    // private String username;
    private String fullName;
    private String emailId;
    private String phoneNumber;
    private int age;
    private Date dob;
    private String empId;
    private String organisationName;
    private String profilePicUrl;
    private List<Cars> userCars;
    private Date createdDate = new Date();
}
