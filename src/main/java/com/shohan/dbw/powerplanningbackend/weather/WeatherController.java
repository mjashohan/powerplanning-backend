package com.shohan.dbw.powerplanningbackend.weather;

import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.Hourly;
import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.WeatherApiService;
import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.WeatherDataPayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherApiService weatherApiService;

    public WeatherController(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    @GetMapping("/weather/{latitude}/{longitude}")
    public WeatherDataPayload getWeather(@PathVariable Float latitude, @PathVariable Float longitude) {
        return weatherApiService.getWeatherData(latitude, longitude);
    }

    @GetMapping("/weather/{latitude}/{longitude}/getcorevalues")
    public List<String> getCoreValues(@PathVariable Float latitude, @PathVariable Float longitude) {
        WeatherDataPayload weather = weatherApiService.getWeatherData(latitude, longitude);
        Hourly coreData = weather.getHourly();
        return coreData.getDirect_normal_irradiance_instant();
    }
}
