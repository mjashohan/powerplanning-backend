package com.shohan.dbw.powerplanningbackend.photovoltaicsystem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shohan.dbw.powerplanningbackend.location.Location;
import com.shohan.dbw.powerplanningbackend.projectplanning.Project;
import com.shohan.dbw.powerplanningbackend.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "photovoltaic_systems")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class PhotovoltaicSystem {

    public PhotovoltaicSystem() {
    }

    public PhotovoltaicSystem(Long systemId, String systemName, String manufacturer, String orientation, double peakValue,
                              Integer area, double tilt, Location location, User user, Project project) {
        super();
        this.systemId = systemId;
        this.systemName = systemName;
        this.manufacturer = manufacturer;
        this.orientation = orientation;
        this.peakValue = peakValue;
        this.area = area;
        this.tilt = tilt;
        this.location = location;
        this.user = user;
        this.project = project;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_id")
    private Long systemId;

    @Column(name = "system_name")
    private String systemName;

    private String manufacturer;

    private String orientation;

    private double peakValue;

    private Integer area;

    private double tilt;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public double getPeakValue() {
        return peakValue;
    }

    public void setPeakValue(double peakValue) {
        this.peakValue = peakValue;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public double getTilt() {
        return tilt;
    }

    public void setTilt(double tilt) {
        this.tilt = tilt;
    }

    @JsonManagedReference
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

