package com.shohan.dbw.powerplanningbackend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/planner")
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/planner/{username}")
    public User getPlanner(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("/signup")
    public User enterNewUser(@RequestBody Signup signup) {
        User existingUser = userRepository.findByUsername(signup.username);
        if(existingUser != null) {
            throw new IllegalArgumentException("Username already exists, please choose another one!");
        }
        User user = new User();
        user.setUserId(null);
        user.setName(signup.username);
        user.setEmail(signup.email);
        user.setPassword(signup.password);

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody Login login) {
        User existingplanner = userRepository.findByUsername(login.username);
        if(existingplanner == null || !login.password.equals(existingplanner.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return existingplanner;
    }

    @PutMapping("/planner/{username}/{id}")
    public User updateUser(@PathVariable String username, @PathVariable Integer id, @RequestBody User user) {
        userRepository.save(user);
        return user;
    }
    @DeleteMapping("/planner/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
