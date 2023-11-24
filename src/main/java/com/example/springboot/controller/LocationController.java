package com.example.springboot.controller;

import com.example.springboot.dto.LocationDto;
import com.example.springboot.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
