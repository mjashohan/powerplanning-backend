package com.shohan.dbw.powerplanningbackend.projectplanning;

import com.shohan.dbw.powerplanningbackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByUser(User user);
    Project findProjectByUser(User user);
}
