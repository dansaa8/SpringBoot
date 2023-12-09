package com.example.springboot.location;

import com.example.springboot.category.CategoryDTO;
import com.example.springboot.category.CategoryRepository;
import com.example.springboot.exception.NotFoundException;
import com.example.springboot.location.request.LocationRequestBody;
import jakarta.transaction.Transactional;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

import static com.example.springboot.location.LocationUtils.getAuthenticatedUserOrThrow;
import static com.example.springboot.location.LocationUtils.setLocationEntityProperties;
import static java.util.stream.Collectors.toList;
import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
public class LocationService {

    private final LocationRepository repository;
    private final LocationDTOMapper locationDTOMapper;
    private final CategoryRepository categoryRepository;

    public LocationService(LocationRepository repository, LocationDTOMapper locationDTOMapper, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.locationDTOMapper = locationDTOMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<LocationDTO> getAllPublic() {
        return repository.findByIsPrivateFalse().stream().map(locationDTOMapper).toList();
    }

    public List<LocationDTO> getPublicByCategory(String name) {
        return repository.findAllByIsPrivateFalseAndCategory_Name(name).stream().map(locationDTOMapper).toList();
    }

    public List<LocationDTO> getPublicInRadius(double lat, double lon, double distance) {
        Point<G2D> coordinate = DSL.point(WGS84, g(lon, lat));
        return repository.filterOnDistance(coordinate, distance).stream().map(locationDTOMapper).toList();
    }

    public LocationDTO getOnePublic(int id) {
        Location location = repository.findByIsPrivateFalseAndId(id)
                .orElseThrow(() -> new NotFoundException("Location with id '" + id + "' not found"));
        return locationDTOMapper.apply(location);
    }

    public List<LocationDTO> getUserLocations(String userId) throws AccessDeniedException {
        Authentication user = getAuthenticatedUserOrThrow();

        if(Objects.equals(user.getName(), userId)) {
            return repository.findMyLocations(userId).stream().map(locationDTOMapper).toList();
        }
        throw new AccessDeniedException("Access denied");
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
