package com.example.springboot.location;

import com.example.springboot.constraint.coordinate.Latitude;
import com.example.springboot.constraint.coordinate.Longitude;
import com.example.springboot.constraint.location.LocationIdMustExist;
import com.example.springboot.location.request.LocationRequestBody;
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

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping("/locations")
    public List<LocationDTO> getAllPublicLocations() { return service.getAllPublic();}

    @GetMapping("/locations/{id}")
    public LocationDTO getOnePublicLocation(@PathVariable @LocationIdMustExist int id) {
        return service.getOnePublic(id);
    }

    @GetMapping(path = "/locations", params = "categoryName")
    public List<LocationDTO> getPublicLocationsByCategory(@RequestParam String categoryName) {
        return service.getPublicByCategory(categoryName);
    }

    @GetMapping(value = "/locations", params = {"lat", "lon", "distance"})
    public List<LocationDTO> getPublicLocationsInRadius(
            @RequestParam @Latitude double lat,
            @RequestParam @Longitude double lon,
            @RequestParam @NotNull @Min(0) double distance) {
        return service.getPublicInRadius(lat, lon, distance);
    }

    @GetMapping("/users/{userId}/locations")
    public List<LocationDTO> getUserLocations(@PathVariable String userId) throws AccessDeniedException {
        return service.getUserLocations(userId);
    }

    @PostMapping("/locations")
    public ResponseEntity<String> addLocation(@RequestBody @Valid LocationRequestBody requestBody) throws AccessDeniedException {
        service.add(requestBody);
        return ResponseEntity.ok("Location successfully added");
    }

    @PutMapping("/locations/{id}")
    public void updateLocation(
            @PathVariable @Valid @LocationIdMustExist int id,
            @RequestBody @Valid LocationRequestBody requestBody) throws AccessDeniedException {
        service.update(id, requestBody);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable @Valid @LocationIdMustExist int id) throws AccessDeniedException {
        service.delete(id);
    }
}
