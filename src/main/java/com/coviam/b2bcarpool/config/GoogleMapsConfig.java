package com.coviam.b2bcarpool.config;

import com.google.maps.GeoApiContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component("com.coviam.b2bcarpool.config.GoogleMapsConfig")
public class GoogleMapsConfig {

    @Autowired
    private Environment environment;

    private GeoApiContext context;

    public GoogleMapsConfig() {

    }

    public GeoApiContext getContext() {
        if (context == null) {
            String apiKey = environment.getProperty("GOOGLE_API_KEY");
            log.info("API_KEY--> " + apiKey);
            context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();
        }
        return context;
    }
}
