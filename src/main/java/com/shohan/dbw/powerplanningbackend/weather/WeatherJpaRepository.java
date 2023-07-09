package com.shohan.dbw.powerplanningbackend.weather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherJpaRepository extends JpaRepository<WeatherData, Long> {

}
