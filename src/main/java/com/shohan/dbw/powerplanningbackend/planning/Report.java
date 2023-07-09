package com.shohan.dbw.powerplanningbackend.planning;

import com.shohan.dbw.powerplanningbackend.projectplanning.Project;
import jakarta.persistence.*;


public class Report {

    public Report(Integer reportId, double powerPeak, String orientation, double inclination, double area, double longitude, double latitude, Project project) {
        super();
        this.reportId = reportId;
        this.powerPeak = powerPeak;
        this.orientation = orientation;
        this.inclination = inclination;
        this.area = area;
        this.longitude = longitude;
        this.latitude = latitude;
        this.project = project;
    }

    public Report() {}


    private Integer reportId;
    private double powerPeak;
    private String orientation;
    private double inclination;
    private double area;
    private double longitude;
    private double latitude;
    
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public double getPowerPeak() {
        return powerPeak;
    }

    public void setPowerPeak(double powerPeak) {
        this.powerPeak = powerPeak;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", powerPeak=" + powerPeak +
                ", orientation='" + orientation + '\'' +
                ", inclination=" + inclination +
                ", area=" + area +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", project=" + project +
                '}';
    }
}
