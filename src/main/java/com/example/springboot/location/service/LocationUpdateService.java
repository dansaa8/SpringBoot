package com.example.springboot.location.service;

import com.example.springboot.category.CategoryRepository;
import com.example.springboot.exception.NotFoundException;
import com.example.springboot.location.entity.Location;
import com.example.springboot.location.repository.LocationRepository;
import com.example.springboot.location.requestbody.LocationRequestBody;
import jakarta.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

import static com.example.springboot.location.service.LocationUtils.getAuthenticatedUserOrThrow;
import static com.example.springboot.location.service.LocationUtils.setLocationEntityProperties;

@Service
public class LocationUpdateService {

    private final LocationRepository repository;
    private final CategoryRepository categoryRepository;

    public LocationUpdateService(LocationRepository repository, CategoryRepository categoryRepository) {

        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public void add(LocationRequestBody location) throws AccessDeniedException {
        Authentication user = getAuthenticatedUserOrThrow();

        var locationEntity = new Location();
        locationEntity.setUserId(user.getName());
        handleLocationProcessing(locationEntity, location);
        repository.save(locationEntity);
    }

    @Transactional
    public void update(int id, LocationRequestBody location) throws AccessDeniedException {
        Authentication user = getAuthenticatedUserOrThrow();

        var locationEntity = findOwnedLocationOrThrow(user.getName(), id);

        handleLocationProcessing(locationEntity, location);
        repository.save(locationEntity);
    }

    @Transactional
    public void delete(int id) throws AccessDeniedException {
        Authentication user = getAuthenticatedUserOrThrow();

        var locationEntity = findOwnedLocationOrThrow(user.getName(), id);

        repository.delete(locationEntity);
    }



    private void handleLocationProcessing(Location locationEntity, LocationRequestBody locationRequestBody) {
        var fkCategoryEntity = categoryRepository.findByNameIgnoreCase(locationRequestBody.categoryName())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        setLocationEntityProperties(locationEntity, fkCategoryEntity, locationRequestBody);
    }

    private Location findOwnedLocationOrThrow(String userId, Integer locationId) throws AccessDeniedException {
        return repository.findLocationByUserIdAndId(userId, locationId)
                .orElseThrow(() -> new AccessDeniedException("Access denied"));
    }
}

