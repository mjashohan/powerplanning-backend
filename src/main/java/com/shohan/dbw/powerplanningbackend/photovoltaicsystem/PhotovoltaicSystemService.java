package com.shohan.dbw.powerplanningbackend.photovoltaicsystem;

import com.shohan.dbw.powerplanningbackend.location.Location;
import com.shohan.dbw.powerplanningbackend.weather.WeatherData;
import com.shohan.dbw.powerplanningbackend.weather.WeatherService;
import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.Hourly;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotovoltaicSystemService {

    private final JavaMailSender javaMailSender;

    public PhotovoltaicSystemService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public double calculatePowerPeak(String manufacturer, double irradiance) {
        double standardIrradiance = 800.0;
        double temperatureCoefficient = 0.0;
        double vmp = 0.0;
        if(manufacturer.equals("Hanhwa Q-cells")) {
            vmp = 29.74;
            temperatureCoefficient = -0.34;

            return vmp + (irradiance - standardIrradiance) * temperatureCoefficient;
        } else if(manufacturer.equals("First Solar")) {
            vmp = 176.3;
            temperatureCoefficient = -0.32;

            return vmp + (irradiance - standardIrradiance) * temperatureCoefficient;
        } else if (manufacturer.equals("JinkoSolar")) {
            vmp = 39.9;
            temperatureCoefficient = -0.29;

            return vmp + (irradiance - standardIrradiance) * temperatureCoefficient;
        } else {
            return 0.0;
        }
    }

    public List<WeatherData> mapHourlyToWeatherData(Hourly hourly, Location location) {
        WeatherService weatherService = new WeatherService();
        List<String> timeList = hourly.getTime();
        List<String> temperatureList = hourly.getTemperature_2m();
        List<String> irradianceList = hourly.getDirect_normal_irradiance_instant();
        List<WeatherData> weatherDataList = new ArrayList<>();

        for(int i = 0; i < timeList.size(); i++) {
            String time = timeList.get(i);
            double temperature = Double.parseDouble(temperatureList.get(i));
            double irradiance = Double.parseDouble(irradianceList.get(i));
            
            WeatherData data = new WeatherData();
            data.setTimestamp(weatherService.convertStringtoTimestamp(time));
            data.setTemperature(temperature);
            data.setIrradiance(irradiance);
            data.setLocation(location);
            weatherDataList.add(data);
        }
        return weatherDataList;
    }

    public double findMaxIrradiance(Hourly hourly) {
        List<String> irradiance = hourly.getDirect_normal_irradiance_instant();
        List<Double> irradiaceDouble = new ArrayList<>();

        for(String str : irradiance) {
            double value = Double.parseDouble(str);
            irradiaceDouble.add(value);
        }

        return irradiaceDouble.stream().mapToDouble(Double::doubleValue)
                .max().orElse(Double.MIN_VALUE);
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }
}
