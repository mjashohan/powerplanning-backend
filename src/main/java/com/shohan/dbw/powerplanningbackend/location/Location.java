package com.shohan.dbw.powerplanningbackend.location;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shohan.dbw.powerplanningbackend.photovoltaicsystem.PhotovoltaicSystem;
import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    public Location() {}

    public Location(Long id, Float latitude, Float longitude, String address, PhotovoltaicSystem photovoltaicSystem) {
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.photovoltaicSystem = photovoltaicSystem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float latitude;
    private Float longitude;
    private String address;

    @JsonIgnore
    @JsonBackReference
    @OneToOne(mappedBy = "location")
    private PhotovoltaicSystem photovoltaicSystem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PhotovoltaicSystem getPhotovoltaicSystem() {
        return photovoltaicSystem;
    }

    public void setPhotovoltaicSystem(PhotovoltaicSystem photovoltaicSystem) {
        this.photovoltaicSystem = photovoltaicSystem;
    }
}
