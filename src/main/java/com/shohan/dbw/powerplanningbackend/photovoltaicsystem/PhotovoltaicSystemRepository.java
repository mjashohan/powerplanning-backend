package com.shohan.dbw.powerplanningbackend.photovoltaicsystem;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PhotovoltaicSystemRepository extends JpaRepository<PhotovoltaicSystem, Long> {
    PhotovoltaicSystem findBySystemName(String systemname);
}
