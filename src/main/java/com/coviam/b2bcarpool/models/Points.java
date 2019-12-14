package com.coviam.b2bcarpool.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Points {
    private double latitude;
    private double longitude;
    private String placeId;
}
