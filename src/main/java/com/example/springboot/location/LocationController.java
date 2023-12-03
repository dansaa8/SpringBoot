package com.example.springboot.location;

import com.example.springboot.constraint.location.LocationIdMustExist;
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
    List<LocationView> getAll() { return service.getAll();}

    @GetMapping("/byCategory")
    List<LocationView> getAllLocations(@RequestParam int categoryId) {
        return service.getAllByCategoryId(categoryId);
    }


    @GetMapping("{id}")
    LocationView getOne(@PathVariable @LocationIdMustExist int id) {
        return service.getOne(id);
    }

    @PostMapping
    public ResponseEntity<String> addLocation(@RequestBody @Valid LocationReqBody requestBody) {
        service.addLocation(requestBody);
        return ResponseEntity.ok("Location successfully added");
    }

    @PutMapping("{id}")
    public void replaceLocation(
            @PathVariable @Valid @LocationIdMustExist int id,
            @RequestBody @Valid LocationReqBody requestBody) {
        service.replaceLocation(id, requestBody);
    }
}
