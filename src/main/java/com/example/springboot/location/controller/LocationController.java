package com.example.springboot.location.controller;

import com.example.springboot.constraint.coordinate.Latitude;
import com.example.springboot.constraint.coordinate.Longitude;
import com.example.springboot.constraint.location.LocationIdMustExist;
import com.example.springboot.location.dto.LocationDTO;
import com.example.springboot.location.dto.LocationDTOWithAddress;
import com.example.springboot.location.requestbody.LocationRequestBody;
import com.example.springboot.location.service.LocationQueryService;
import com.example.springboot.location.service.LocationUpdateService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("api")
@Validated
public class LocationController {

    private final LocationQueryService queryService;
    private final LocationUpdateService updateService;

    public LocationController(LocationQueryService qService, LocationUpdateService uService) {
        this.queryService = qService;
        this.updateService = uService;
    }

//    @GetMapping("/geo")
//        String lookup(@RequestParam float lat, @RequestParam float lon) {
//            return queryService.reverseGeoCode(lat, lon);
//        }

    @GetMapping("/locations")
    public List<LocationDTO> getAllPublicLocations() { return queryService.getAllPublic();}

    @GetMapping("/locations/{id}")
    public LocationDTOWithAddress getOnePublicLocation(@PathVariable @LocationIdMustExist int id) {
        return queryService.getOnePublic(id);
    }

    @GetMapping(path = "/locations", params = "categoryName")
    public List<LocationDTO> getPublicLocationsByCategory(@RequestParam String categoryName) {
        return queryService.getPublicByCategory(categoryName);
    }

    @GetMapping(value = "/locations", params = {"lat", "lon", "distance"})
    public List<LocationDTO> getPublicLocationsInRadius(
            @RequestParam @Latitude double lat,
            @RequestParam @Longitude double lon,
            @RequestParam @NotNull @Min(0) double distance) {
        return queryService.getPublicInRadius(lat, lon, distance);
    }

    @GetMapping("/users/{userId}/locations")
    public List<LocationDTO> getUserLocations(@PathVariable String userId) throws AccessDeniedException {
        return queryService.getUserLocations(userId);
    }

    @PostMapping("/locations")
    public ResponseEntity<String> addLocation(@RequestBody @Valid LocationRequestBody requestBody) throws AccessDeniedException {
        updateService.add(requestBody);
        return ResponseEntity.ok("Location successfully added");
    }

    @PutMapping("/locations/{id}")
    public void updateLocation(
            @PathVariable @Valid @LocationIdMustExist int id,
            @RequestBody @Valid LocationRequestBody requestBody) throws AccessDeniedException {
        updateService.update(id, requestBody);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable @Valid @LocationIdMustExist int id) throws AccessDeniedException {
        updateService.delete(id);
    }
}
