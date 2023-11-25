package com.example.springboot.service;

import com.example.springboot.entity.Location;
import com.example.springboot.repository.CategoryRepository;
import com.example.springboot.repository.LocationRepository;
import com.example.springboot.requestbody.LocationRequestBody;
import org.springframework.stereotype.Service;
import com.example.springboot.dto.LocationDto;

import java.util.List;
import java.util.Optional;

// när locationservice obj skapas som böna, så kommer dependency-injectorn injecta två repositories.
@Service
public class LocationService {

    private final LocationRepository repository;
    private final CategoryRepository categoryRepository;
    // anv. när vi ska lägga till ny Location. Måste fråga om DB categoryn som ska läggas till finns

    public LocationService(LocationRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<LocationDto> getAllLocations() {
        return repository.findByIsPrivateFalse()
                .stream()
                .map(LocationDto::new)
                .toList();
    }

    public List<LocationDto> getAllPublicLocationsByCategory(String categoryName) {
        return repository.findByIsPrivateFalseAndCategoryName(categoryName)
                .stream()
                .map(LocationDto::new)
                .toList();
    }

    Optional<LocationDto> map(Optional<Location> location) {
        if (location.isEmpty())
            return Optional.empty();
        var foundLocation = location.get();
        return Optional.of(
                new LocationDto(foundLocation)
        );
    }

    public void addLocation(LocationRequestBody location) {
        Location locationEntity = new Location();
    }
}
