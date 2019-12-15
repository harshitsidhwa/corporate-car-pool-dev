package com.coviam.b2bcarpool.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Points {
    private double latitude;
    private double longitude;
    private String placeId;
    private String placeAddress;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "Points{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}
