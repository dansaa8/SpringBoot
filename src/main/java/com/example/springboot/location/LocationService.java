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

    public List<LocationView> getAllByCategoryId(int id) {
        return repository.findAllByCategoryId(id);
    }


    public LocationView getOne(int id){
        return repository.findViewById(id).orElseThrow(() ->
                new NotFoundException("Location with id '" + id + "' not found"));
    }

    public void addLocation(LocationReqBody location) {
        if (repository.existsByName(location.name()))
            throw new DuplicateEntryException(location.name() + " already in use");

        var locationEntity = new Location();
        handleLocationProcessing(locationEntity, location);
        repository.save(locationEntity);
    }

    @Transactional
    public void replaceLocation(int id, LocationReqBody location) {
        var locationEntity  = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Location with " + id + " not found"));

        if (repository.existsByNameExcludingId(location.name(), id))
            throw new DuplicateEntryException(location.name() + " already in use");

        handleLocationProcessing(locationEntity, location);
        repository.save(locationEntity);
    }

    private void handleLocationProcessing(Location locationEntity, LocationReqBody location) {
        var fkCategoryEntity = categoryRepository.findByNameIgnoreCase(location.categoryName())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        setLocationEntityProperties(locationEntity, fkCategoryEntity, location);
    }

}
