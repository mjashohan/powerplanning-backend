package com.shohan.dbw.powerplanningbackend.projectplanning;

import com.shohan.dbw.powerplanningbackend.user.User;
import com.shohan.dbw.powerplanningbackend.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    ProjectRepository projectRepository;
    UserRepository userRepository;

    public ProjectController(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping ("/projects/{planner}")
    public List<Project> getAllProjects(@PathVariable String planner){
        User userName = userRepository.findByUsername(planner);
        if(planner != null) {
            return projectRepository.findByUser(userName);
        } else {
            throw new IllegalArgumentException("Project with this user does not exist");
        }
    }

    @PostMapping("/projects")
    public Project addNewProjects(@RequestBody ProjectDTO projectDTO) {
        User user = userRepository.findByUsername(projectDTO.username);
        Project project = new Project();
        project.setProjectId(null);
        project.setProjectName(projectDTO.projectName);
        project.setPlanner(user);
        return projectRepository.save(project);
    }
}
