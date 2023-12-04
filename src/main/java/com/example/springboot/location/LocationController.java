package com.example.springboot.location;

import com.example.springboot.constraint.location.LocationIdMustExist;
import jakarta.validation.Valid;
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
    List<LocationView> getAllPublic() { return service.getAllPublic();}

    @GetMapping("/locations{id}")
    LocationView getOnePublic(@PathVariable @LocationIdMustExist int id) {
        return service.getOnePublic(id);
    }


    @GetMapping(path = "/locations", params = "categoryName")
    List<LocationView> getAllLocations(@RequestParam String categoryName) {
        return service.getAllPublicByCategory(categoryName);
    }

    @GetMapping("/users/{userId}/locations")
    List<LocationView> getMyLocations(@PathVariable String userId) throws AccessDeniedException {
        return service.getMyLocations(userId);
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
