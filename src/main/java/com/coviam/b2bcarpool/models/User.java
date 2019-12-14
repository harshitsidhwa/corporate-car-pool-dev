package com.coviam.b2bcarpool.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"phoneNumber"})
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
