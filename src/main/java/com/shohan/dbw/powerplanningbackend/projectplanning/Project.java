package com.shohan.dbw.powerplanningbackend.projectplanning;

import com.shohan.dbw.powerplanningbackend.user.User;
import jakarta.persistence.*;

@Entity
public class Project {

    public Project(Integer projectId, String projectName, Double powerGenerated, User user) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.powerGenerated = powerGenerated;
        this.user = user;
    }
    public Project() {}

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Integer projectId;
    private String projectName;
    private Double powerGenerated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public User getPlanner() {
        return this.user;
    }

    public void setPlanner(User user) {
        this.user = user;
    }

    public Double getPowerGenerated() {
        return powerGenerated;
    }

    public void setPowerGenerated(Double powerGenerated) {
        this.powerGenerated = powerGenerated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", planner=" + user +
                '}';
    }
}
