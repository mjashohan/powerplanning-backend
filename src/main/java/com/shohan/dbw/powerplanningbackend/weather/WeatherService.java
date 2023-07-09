package com.shohan.dbw.powerplanningbackend.weather;

import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.Hourly;
import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.WeatherApiService;
import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.WeatherDataPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class WeatherService {

    private WeatherApiService weatherApiService;

    @Autowired
    public WeatherService(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }
    public WeatherService() {};
    public Hourly getCoreWeatherData(Float latitude, Float longitude) {
        WeatherDataPayload weather = weatherApiService.getWeatherData(latitude, longitude);
        return weather.getHourly();
    }

    public Timestamp convertStringtoTimestamp(String timeString) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timeString, inputFormatter);
            String formattedDate = dateTime.format(outputFormatter);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsedDate = dateFormat.parse(formattedDate);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
