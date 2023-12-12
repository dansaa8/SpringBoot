package com.example.springboot.location.service;

import com.example.springboot.category.Category;
import com.example.springboot.location.entity.Location;
import com.example.springboot.location.requestbody.LocationRequestBody;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.file.AccessDeniedException;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

public class LocationUtils {

    private LocationUtils(){}

    protected static void setLocationEntityProperties(
            Location locationEntity,
            Category fkCategoryEntity,
            LocationRequestBody location) {

        var geo = createPointGeometry(location);

        locationEntity.setName(location.name());
        locationEntity.setDescription(location.description());
        locationEntity.setIsPrivate(location.isPrivate());
        locationEntity.setCoordinate(geo);
        locationEntity.setCategory(fkCategoryEntity);
    }

    protected static Point<G2D> createPointGeometry(LocationRequestBody location) {
        return Geometries.mkPoint(
                new G2D(location.coordinate().lon(),
                        location.coordinate().lat()), WGS84);
    }

    protected static Authentication getAuthenticatedUserOrThrow() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new AccessDeniedException("Authentication is required");
        }
        return authentication;
    }
}