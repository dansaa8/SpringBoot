package com.example.springboot.controller;

import com.example.springboot.dto.LocationDto;
import com.example.springboot.entity.Location;
import com.example.springboot.requestbody.LocationRequestBody;
import com.example.springboot.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    List<LocationDto> getAllPublic() { return service.getAllLocations();}

    @GetMapping("/byCategory")
    List<LocationDto> getAllPublicFromCategory(@RequestParam String category) {
        return service.getAllPublicLocationsByCategory(category);
    }

    @PostMapping
    public void addOne(@RequestBody LocationRequestBody location) {
        System.out.println(location);
        service.addLocation(location);
    }
}
