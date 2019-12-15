package com.coviam.b2bcarpool.helper;

import com.coviam.b2bcarpool.config.GoogleMapsConfig;
import com.coviam.b2bcarpool.models.Points;
import com.google.maps.DirectionsApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component("com.coviam.b2bcarpool.helper.DirectionsHelper")
public class DirectionsHelper {

    @Autowired
    private GoogleMapsConfig googleMapsConfig;

    @Autowired
    private Environment environment;

    public boolean isPickupNearBy(Points pickupPointA, Points pickupPointB) {
        LatLng pointA = new LatLng(pickupPointA.getLatitude(), pickupPointA.getLongitude());
        LatLng pointB = new LatLng(pickupPointB.getLatitude(), pickupPointB.getLongitude());
        DirectionsResult result = null;
        try {
            result = DirectionsApi.getDirections(googleMapsConfig.getContext(), pointA.toString(), pointB.toString())
                    .alternatives(false)
                    .mode(TravelMode.WALKING)
                    .units(Unit.METRIC)
                    .await();
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        if (result != null) {
            return result.routes[0].legs[0].distance.inMeters <= Integer.parseInt(Objects.requireNonNull(environment.getProperty("minDistanceInMetersBetween2PickupPoints")));
        }
        return false;
    }
}
