package com.coviam.b2bcarpool.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cars {

    @Id
    private String carId;
    private String userId;
    private String carNumber;
    private String carType;
    private String carName;
}
