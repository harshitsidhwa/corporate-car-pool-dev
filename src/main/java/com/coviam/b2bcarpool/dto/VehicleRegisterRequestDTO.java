package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class VehicleRegisterRequestDTO {
    private String vehicleName;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleColor;
}
