package com.example.springboot.location;

import com.example.springboot.constraints.LocationIdMustExist;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/locations")
@Validated
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    List<LocationDTO> getAllPublic() { return service.getAllLocations();}

//    @GetMapping("/byCategory")
//    List<LocationDTO> getAllPublicFromCategory(@RequestParam String category) {
//        return service.getAllPublicLocationsByCategory(category);
//    }

    @PostMapping
    public ResponseEntity<String> addOne(@RequestBody @Valid LocationRequestBody requestBody) {
        service.addLocation(requestBody);
        return ResponseEntity.ok("Location successfully added");
    }

    @PatchMapping("{id}")
    public void changeLocation(
            @PathVariable @Valid @LocationIdMustExist  Integer id,
            @RequestBody @Valid LocationRequestBody requestBody) {
        service.updateLocation(id, requestBody);
    }
}
