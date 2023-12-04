package com.example.springboot.location;

import com.example.springboot.category.CategoryRepository;
import com.example.springboot.exception.DuplicateEntryException;
import com.example.springboot.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

import static com.example.springboot.location.LocationUtils.setLocationEntityProperties;
import static org.geolatte.geom.builder.DSL.g;

@Service
public class LocationService {

    private final LocationRepository repository;
    private final CategoryRepository categoryRepository;

    public LocationService(LocationRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<LocationView> getAllPublic() {
        return repository.findByIsPrivateFalse();
    }

    public LocationView getOnePublic(int id){
        return repository.findByIsPrivateFalseAndAndId(id).orElseThrow(() ->
                new NotFoundException("Location with id '" + id + "' not found"));
    }

    public List<LocationView> getAllPublicByCategory(String name) {
        return repository.findAllByIsPrivateFalseAndCategory_Name(name);
    }

    public List<LocationView> getMyLocations(String userId) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.equals(authentication.getName(), userId)) {
            return repository.findMyLocations(userId);
        }
        throw new AccessDeniedException("Access denied");
    }

    public void addLocation(LocationReqBody location) throws AccessDeniedException {
        Authentication authUser = getAuthenticatedUserOrThrowAccessDeniedException();

        if (repository.existsByName(location.name()))
            throw new DuplicateEntryException(location.name() + " already in use");

        var locationEntity = new Location();
        locationEntity.setUserId(authUser.getName());
        handleLocationProcessing(locationEntity, location);
        repository.save(locationEntity);
    }

    @Transactional
    public void replaceLocation(int id, LocationReqBody location) throws AccessDeniedException {
        Authentication authUser = getAuthenticatedUserOrThrowAccessDeniedException();

        if (!repository.isUserOwningEntity(id, authUser.getName()))
            throw new AccessDeniedException("Access denied");

        var locationEntity  = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Location with " + id + " not found"));

        if (repository.existsByNameExcludingId(location.name(), id))
            throw new DuplicateEntryException(location.name() + " already in use");

        handleLocationProcessing(locationEntity, location);
        repository.save(locationEntity);
    }

    private void handleLocationProcessing(Location locationEntity, LocationReqBody locationReqBody) {
        var fkCategoryEntity = categoryRepository.findByNameIgnoreCase(locationReqBody.categoryName())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        setLocationEntityProperties(locationEntity, fkCategoryEntity, locationReqBody);
    }

    private Authentication getAuthenticatedUserOrThrowAccessDeniedException() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new AccessDeniedException("Authentication is required");
        }
        return authentication;
    }


}
