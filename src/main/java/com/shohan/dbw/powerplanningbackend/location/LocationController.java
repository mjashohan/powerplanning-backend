package com.shohan.dbw.powerplanningbackend.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationRepository locationRepository;
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationRepository locationRepository, LocationService locationService) {
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody LocationDTO location) {
        Location createdLocation = new Location();
        createdLocation.setLatitude(location.latitude);
        createdLocation.setLongitude(location.longitude);
        locationRepository.save(createdLocation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) throws Exception {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new Exception("Location not found with id: " + id));
        return ResponseEntity.ok(location);
    }

    @GetMapping("/address/{latitude}/{longitude}")
    public String getAddress(@PathVariable Float latitude, @PathVariable Float longitude){
        try{
            return locationService.findAddress(latitude, longitude);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occured";
        }
    }
}
