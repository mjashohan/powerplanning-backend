package com.shohan.dbw.powerplanningbackend.weather;

import com.shohan.dbw.powerplanningbackend.location.Location;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "weather_data")
public class WeatherData {

    public WeatherData(Long id, Timestamp timestamp, double temperature, double irradiance, Location location) {
        super();
        this.id = id;
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.irradiance = irradiance;
        this.location = location;
    }

    public WeatherData() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp timestamp;
    private double temperature;
    private double irradiance;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getIrradiance() {
        return irradiance;
    }

    public void setIrradiance(double irradiance) {
        this.irradiance = irradiance;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
