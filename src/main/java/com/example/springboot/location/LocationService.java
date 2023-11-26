package com.example.springboot.location;

import com.example.springboot.category.Category;
import com.example.springboot.category.CategoryRepository;
import com.example.springboot.location.requestbodies.LocationRequestBody;
import jakarta.transaction.Transactional;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

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

        double lon = location.coordinate().lon();
        double lat = location.coordinate().lat();

        if (lat < -90 || lat > 90 || lon < -180 || lon > 180)
            throw new IllegalArgumentException("Invalid latitude or longitude");

        var geo = Geometries.mkPoint(new G2D(lon, lat), WGS84);

        locationEntity.setName(location.name());
        locationEntity.setDescription(location.name());
        locationEntity.setIsPrivate(location.isPrivate());
        locationEntity.setCoordinate(geo);

        Optional category = categoryRepository.findByNameIgnoreCase(location.categoryName());
        locationEntity.setCategory((Category) category.get());

        repository.save(locationEntity);
    }

    @Transactional
    public void updateLocation(int id, LocationRequestBody location) {

        double lon = location.coordinate().lon();
        double lat = location.coordinate().lat();

        if (lat < -90 || lat > 90 || lon < -180 || lon > 180)
            throw new IllegalArgumentException("Invalid latitude or longitude");


        var geo = Geometries.mkPoint(new G2D(lon, lat), WGS84);
        var locationEntity  = repository.findById(id).orElseThrow();
        locationEntity.setName(location.name());
        locationEntity.setDescription(location.description());
        locationEntity.setIsPrivate(location.isPrivate());
        locationEntity.setCoordinate(geo);

        repository.save(locationEntity); // Behövs inte, men för tydlighets skull. P.ga. @Transactional
    }
}
