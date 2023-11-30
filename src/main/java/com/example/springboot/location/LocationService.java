package com.example.springboot.location;

import com.example.springboot.category.CategoryRepository;
import com.example.springboot.exception.DuplicateEntryException;
import com.example.springboot.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.example.springboot.location.LocationUtils.mapToLocationDTOList;
import static com.example.springboot.location.LocationUtils.setLocationEntityProperties;
import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
public class LocationService {

    private final LocationRepository repository;
    private final CategoryRepository categoryRepository;

    public LocationService(LocationRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<LocationView> getAll() {
        return repository.findAllBy();
    }

    public List<LocationDTO> getAllByCategoryId(int id) {
        return mapToLocationDTOList(repository.findAllByCategoryId(id));
    }

    public List<Location> findAround(double lat, double lng, double distance) {
        Point<G2D> coordinate = DSL.point(WGS84, g(lng, lat));
        return repository.filterOnDistance(coordinate, distance);
    }

    LocationDTO getOne(int id){
        var locationEntity  = repository.findById(id).orElseThrow(() -> new NotFoundException("Location not found"));
        return new LocationDTO(locationEntity);
    }

    public void addLocation(LocationRequestBody reqBody) {
        if (repository.existsByName(reqBody.name()))
            throw new DuplicateEntryException("Location name already exists");

        var locationEntity = new Location();
        handleLocationProcessing(locationEntity, reqBody);
        repository.save(locationEntity);
    }

    @Transactional
    public void updateLocation(int id, LocationRequestBody requestBody) {
        var locationEntity  = repository.findById(id).orElseThrow(() -> new NotFoundException("Location not found"));
        handleLocationProcessing(locationEntity, requestBody);
        repository.save(locationEntity);
    }


    private void handleLocationProcessing(Location locationEntity, LocationRequestBody reqBody) {
        var fkCategoryEntity = categoryRepository.findByNameIgnoreCase(reqBody.categoryName())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        setLocationEntityProperties(locationEntity, fkCategoryEntity, reqBody);
    }

}
