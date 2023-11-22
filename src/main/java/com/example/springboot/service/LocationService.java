package com.example.springboot.service;

import com.example.springboot.dto.CategoryNameSymbol;
import com.example.springboot.dto.LocationDto;
import com.example.springboot.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<LocationDto> getAllLocations() {
        return repository.findAll().stream()
                .map(LocationDto::new)
                .toList();
    }
}
