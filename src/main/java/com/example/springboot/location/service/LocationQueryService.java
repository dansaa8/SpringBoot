package com.example.springboot.location.service;

import com.example.springboot.exception.NotFoundException;
import com.example.springboot.location.api.GeocodingApiConnector;
import com.example.springboot.location.dto.LocationDTO;
import com.example.springboot.location.dto.LocationDTOWithAddress;
import com.example.springboot.location.entity.Location;
import com.example.springboot.location.repository.LocationRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.springboot.location.service.LocationUtils.getAuthenticatedUserOrThrow;
import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
@EnableRetry
public class LocationQueryService {

    private static final Logger logger = LoggerFactory.getLogger(LocationQueryService.class);

    GeocodingApiConnector geocodingApiConnector;
    private final LocationRepository repository;

    public LocationQueryService(LocationRepository repository, GeocodingApiConnector geocodingApiConnector) {
        this.repository = repository;
        this.geocodingApiConnector = geocodingApiConnector;
    }

    public List<LocationDTO> getAllPublic() {
        return repository.findByIsPrivateFalse().stream().map(LocationDTO::new).toList();
    }

    public List<LocationDTO> getPublicByCategory(String name) {
        return  repository.findAllByIsPrivateFalseAndCategory_Name(name).stream().map(LocationDTO::new).toList();
    }

    public List<LocationDTO> getPublicInRadius(double lat, double lon, double distance) {
        Point<G2D> coordinate = DSL.point(WGS84, g(lon, lat));
        return repository.filterOnDistance(coordinate, distance).stream().map(LocationDTO::new).toList();
    }

    public LocationDTOWithAddress getOnePublic(int id) {
        Location location = repository.findByIsPrivateFalseAndId(id)
                .orElseThrow(() -> new NotFoundException("Location with id '" + id + "' not found"));
        return new LocationDTOWithAddress(location, getAddress(location));
    }

    public List<LocationDTO> getUserLocations(String userId) throws AccessDeniedException {
        Authentication user = getAuthenticatedUserOrThrow();

        if(Objects.equals(user.getName(), userId)) {
            return repository.findMyLocations(userId).stream().map(LocationDTO::new).toList();
        }
        throw new AccessDeniedException("Access denied");
    }


    private Map<String, Object> getAddress(Location location) {
        var lon = (float) location.getCoordinate().getPosition().getLon();
        var lat = (float) location.getCoordinate().getPosition().getLat();
        return geocodingApiConnector.getAddress(lat, lon);
    }
}
