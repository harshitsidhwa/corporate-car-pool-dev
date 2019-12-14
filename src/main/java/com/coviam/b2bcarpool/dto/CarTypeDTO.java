package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class CarTypeDTO {
    private String carType;

    public CarTypeDTO(String carType) {
        this.carType = carType;
    }
}
