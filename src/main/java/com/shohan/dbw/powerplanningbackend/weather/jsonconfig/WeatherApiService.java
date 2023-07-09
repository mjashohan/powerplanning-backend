package com.shohan.dbw.powerplanningbackend.weather.jsonconfig;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiService {
    // weather api: https://api.open-meteo.com/v1/forecast?latitude=49.44&longitude=11.07&hourly=temperature_2m,direct_normal_irradiance_instant&current_weather=true&timezone=Europe%2FBerlin&forecast_days=1
    private final String API_URL = "https://api.open-meteo.com/v1/forecast";

    public WeatherDataPayload getWeatherData(Float latitude, Float longitude) {
        String apiUrl = API_URL + "?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m,direct_normal_irradiance_instant";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, WeatherDataPayload.class);
    }
}
