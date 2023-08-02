package com.shohan.dbw.powerplanningbackend.location;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationService {

    private GeoApiContext geoApiContext;

    public  LocationService() {
        geoApiContext = new GeoApiContext.Builder().apiKey("INSERT_MAP_API_HERE").build();
    }

    public String findAddress(Float latitude, Float longitude) throws IOException, InterruptedException, ApiException {
        GeocodingResult[] results = GeocodingApi.reverseGeocode(geoApiContext, new com.google.maps.model.LatLng(latitude, longitude)).await();
        if (results.length > 0) {
            return results[0].formattedAddress;
        } else {
            return "Address not found";
        }
    }
}
