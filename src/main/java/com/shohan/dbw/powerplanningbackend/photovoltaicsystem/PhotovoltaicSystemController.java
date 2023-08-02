package com.shohan.dbw.powerplanningbackend.photovoltaicsystem;

import com.google.maps.errors.ApiException;
import com.shohan.dbw.powerplanningbackend.location.Location;
import com.shohan.dbw.powerplanningbackend.location.LocationRepository;
import com.shohan.dbw.powerplanningbackend.location.LocationService;
import com.shohan.dbw.powerplanningbackend.projectplanning.Project;
import com.shohan.dbw.powerplanningbackend.projectplanning.ProjectRepository;
import com.shohan.dbw.powerplanningbackend.user.User;
import com.shohan.dbw.powerplanningbackend.user.UserRepository;
import com.shohan.dbw.powerplanningbackend.weather.WeatherData;
import com.shohan.dbw.powerplanningbackend.weather.WeatherJpaRepository;
import com.shohan.dbw.powerplanningbackend.weather.WeatherService;
import com.shohan.dbw.powerplanningbackend.weather.jsonconfig.Hourly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photovoltaic-systems")
public class PhotovoltaicSystemController {

    private final PhotovoltaicSystemRepository photovoltaicSystemRepository;
    private final PhotovoltaicSystemService photovoltaicSystemService;
    private final LocationRepository locationRepository;
    private final WeatherService weatherService;
    private final LocationService locationService;
    private final WeatherJpaRepository weatherJpaRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public PhotovoltaicSystemController(PhotovoltaicSystemRepository photovoltaicSystemRepository, PhotovoltaicSystemService photovoltaicSystemService, LocationRepository locationRepository,
                                        WeatherService weatherService, LocationService locationService, 
                                        WeatherJpaRepository weatherJpaRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.photovoltaicSystemRepository = photovoltaicSystemRepository;
        this.photovoltaicSystemService = photovoltaicSystemService;
        this.locationRepository = locationRepository;
        this.weatherService = weatherService;
        this.locationService = locationService;
        this.weatherJpaRepository = weatherJpaRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<PhotovoltaicSystem> getAllPhotovoltaicSystems() {
        List<PhotovoltaicSystem> systems = photovoltaicSystemRepository.findAll();
        return systems;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PhotovoltaicSystem>> getPhotovoltaicSystemById(@PathVariable("id") Long id) {
        Optional<PhotovoltaicSystem> system = photovoltaicSystemRepository.findById(id);
        if (system.isPresent()) {
            return new ResponseEntity<>(system, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public PhotovoltaicSystem createPhotovoltaicSystem(@RequestBody SystemPayload system) throws IOException, InterruptedException, ApiException {
        User user = userRepository.findByUsername(system.username);
        Hourly hourly = weatherService.getCoreWeatherData(Float.parseFloat(system.latitude), Float.parseFloat(system.longitude));

        System.out.println(system.manufacturer);
        System.out.println(system.area);
        System.out.println(system.latitude);
        System.out.println(system.longitude);
        System.out.println(system.tilt);
        PhotovoltaicSystem createdSystem = new PhotovoltaicSystem();
        createdSystem.setSystemId(null);
        createdSystem.setSystemName(system.systemName);
        createdSystem.setManufacturer(system.manufacturer);
        createdSystem.setOrientation(system.orientation);
        double powerPeak = photovoltaicSystemService.calculatePowerPeak(system.manufacturer, photovoltaicSystemService.findMaxIrradiance(hourly));
        createdSystem.setPeakValue(powerPeak);
        createdSystem.setArea(Integer.parseInt(system.area));
        createdSystem.setTilt(Double.parseDouble(system.tilt));
        createdSystem.setUser(user);
        photovoltaicSystemRepository.save(createdSystem); // Save the PhotovoltaicSystem entity first

        Location createdLocation = new Location();
        createdLocation.setId(null);
        createdLocation.setLatitude(Float.parseFloat(system.latitude));
        createdLocation.setLongitude(Float.parseFloat(system.longitude));
        String address = locationService.findAddress(Float.parseFloat(system.latitude), Float.parseFloat(system.longitude));
        createdLocation.setAddress(address);
        createdLocation.setPhotovoltaicSystem(createdSystem);
        locationRepository.save(createdLocation); // Save the Location entity

        createdSystem.setLocation(createdLocation); // Update the relationship in the PhotovoltaicSystem entity
        photovoltaicSystemRepository.save(createdSystem); // Save the PhotovoltaicSystem entity again

        WeatherData createdWeather = new WeatherData();
        createdWeather.setId(null);
        List<WeatherData> weatherData = photovoltaicSystemService.mapHourlyToWeatherData(hourly, createdLocation);
        weatherJpaRepository.saveAll(weatherData);
        Project project = new Project();
        double powerGenerated = photovoltaicSystemService.calulatePowerGenerated(powerPeak, photovoltaicSystemService.findMaxIrradiance(hourly), Integer.parseInt(system.area));
        project.setProjectId(null);
        project.setProjectName(system.systemName);
        project.setPowerGenerated(powerGenerated);
        project.setUser(user);
        projectRepository.save(project);
        createdSystem.setProject(project);
        photovoltaicSystemRepository.save(createdSystem);
        return createdSystem;
        //return new ResponseEntity<>(createdSystem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotovoltaicSystem> updatePhotovoltaicSystem(
            @PathVariable("id") Long id, @RequestBody PhotovoltaicSystem system) {
        PhotovoltaicSystem updatedSystem = photovoltaicSystemRepository.save(system);
        return new ResponseEntity<>(updatedSystem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotovoltaicSystem(@PathVariable String id) {
        photovoltaicSystemRepository.deleteById(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/sendEmail")
        public String sendEmail(@RequestBody List<EmailDTO> email) {
        User user = userRepository.findByUsername(email.get(0).username);
        String to = user.getEmail();
        String subject = "Reports for" + email.get(0).username+ "are here!";
        List<String> body = new ArrayList<>();
        for (EmailDTO emailDTO : email) {
            body.add("Power peak: " + emailDTO.powerPeak + "\n"
                    + "Orientation: " + emailDTO.orientation + "\n"
                    + "Tilt: " + emailDTO.tilt + "\n"
                    + "Area: " + emailDTO.area + "\n"
                    + "Latitude: " + emailDTO.latitude + "\n"
                    + "Longitude: " + emailDTO.longitude + "\n"
                    + "----------------------------- \n"
                    + "Total Power Generated: " + emailDTO.powerGenerated + "kWh");

        }
        photovoltaicSystemService.sendEmail(to, subject, body);

        return "Email sent!";
    }
}
