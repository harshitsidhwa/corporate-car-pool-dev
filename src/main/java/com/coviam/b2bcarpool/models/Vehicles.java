package com.coviam.b2bcarpool.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Vehicles {
    private String userId;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleName;
    private String vehicleColor;
}
