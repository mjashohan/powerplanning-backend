package com.shohan.dbw.powerplanningbackend.user;

import com.shohan.dbw.powerplanningbackend.projectplanning.Project;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    public User() {

    }

    public User(Integer userId, String username, String email, String password) {
        super();
        this.userID = userId;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userID;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    public Integer getUserId() {
        return userID;
    }

    public void setUserId(Integer userId) {
        this.userID = userId;
    }

    public String getName() {
        return username;
    }

    public void setName(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Planner{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
