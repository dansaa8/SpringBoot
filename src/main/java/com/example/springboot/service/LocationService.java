package com.example.springboot.service;

import com.example.springboot.dto.LocationDto;
import com.example.springboot.entity.Location;
import com.example.springboot.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<LocationDto> getAllLocations() {
        return repository.findByStatus("public").stream()
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
}
