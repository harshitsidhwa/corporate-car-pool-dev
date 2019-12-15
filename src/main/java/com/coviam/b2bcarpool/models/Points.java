package com.coviam.b2bcarpool.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Points {
    private double latitude;
    private double longitude;
    private String placeId = "";
    private String placeAddress = null;

    public String getPlaceAddress() {
        return placeAddress;
    }

    @Override
    public String toString() {
        return "Points{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", placeId='" + placeId + '\'' +
                ", placeAddress='" + placeAddress + '\'' +
                '}';
    }
}
