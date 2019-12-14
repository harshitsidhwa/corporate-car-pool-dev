package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class CarsDTO {
    private String carNumber;
    private String carType;
    private String carName;

    @Override
    public String toString() {
        return "CarsDTO{" +
                "carNumber='" + carNumber + '\'' +
                ", carType='" + carType + '\'' +
                ", carName='" + carName + '\'' +
                '}';
    }
}
