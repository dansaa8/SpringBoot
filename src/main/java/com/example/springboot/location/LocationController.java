package com.example.springboot.location;

import com.example.springboot.constraint.LocationIdMustExist;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/locations")
@Validated
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    List<LocationView> getAllPublic() { return service.getAll();}

    @GetMapping("/byCategory")
    List<LocationView> getAllLocations(@RequestParam Integer categoryId) {
        return service.getAllByCategoryId(categoryId);
    }

    @GetMapping("/circle")
    public List<Location> getLocationsInCircle(
            @RequestParam(required = false,
                    defaultValue = "0") double lat,
            @RequestParam(required = false,
                    defaultValue = "0") double lng,
            @RequestParam(required = false,
                    defaultValue = "0") double dist) {
//        if (dist == 0.0)
//            return service.getAll();
        return service.findAround(lat, lng, dist);
    }

    @GetMapping("{id}")
    LocationDTO getOne(@PathVariable int id) {
        return service.getOne(id);
    }

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
