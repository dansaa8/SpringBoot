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
    List<LocationView> getAllPublic() { return service.getAll();}

    @GetMapping("/byCategory")
    List<LocationView> getAllLocations(@RequestParam int categoryId) {
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
