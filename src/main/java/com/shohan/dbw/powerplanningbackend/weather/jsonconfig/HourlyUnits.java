package com.shohan.dbw.powerplanningbackend.weather.jsonconfig;

public class HourlyUnits {
    private String time;
    private String temperature_2m;
    private String direct_normal_irradiance_instant;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(String temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public String getDirect_normal_irradiance_instant() {
        return direct_normal_irradiance_instant;
    }

    public void setDirect_normal_irradiance_instant(String direct_normal_irradiance_instant) {
        this.direct_normal_irradiance_instant = direct_normal_irradiance_instant;
    }
}
