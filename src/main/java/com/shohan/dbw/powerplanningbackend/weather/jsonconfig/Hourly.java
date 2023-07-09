package com.shohan.dbw.powerplanningbackend.weather.jsonconfig;


import java.util.List;

public class Hourly {
    private List<String> time;
    private List<String> temperature_2m;
    private List<String> direct_normal_irradiance_instant;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(List<String> temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public List<String> getDirect_normal_irradiance_instant() {
        return direct_normal_irradiance_instant;
    }

    public void setDirect_normal_irradiance_instant(List<String> direct_normal_irradiance_instant) {
        this.direct_normal_irradiance_instant = direct_normal_irradiance_instant;
    }
}
