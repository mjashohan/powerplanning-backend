package com.shohan.dbw.powerplanningbackend.projectplanning;

import com.shohan.dbw.powerplanningbackend.user.User;
import jakarta.persistence.*;

@Entity
public class Project {

    public Project(Integer projectId, String projectName, User user) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.user = user;
    }
    public Project() {}

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Integer projectId;
    private String projectName;
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

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", planner=" + user +
                '}';
    }
}
