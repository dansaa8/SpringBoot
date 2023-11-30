package com.example.springboot.location;

import com.example.springboot.category.Category;
import com.example.springboot.category.CategoryRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

public class LocationUtils {

    private LocationUtils(){}

    public static List<LocationDTO> mapToLocationDTOList(List<Location> locations) {
        return locations.stream()
                .map(LocationDTO::new)
                .collect(Collectors.toList());
    }

    public static void setLocationEntityProperties(
            Location locationEntity,
            Category fkCategoryEntity,
            LocationRequestBody location) {

        var geo = createPointGeometry(location);

        locationEntity.setName(location.name());
        locationEntity.setDescription(location.name());
        locationEntity.setIsPrivate(location.isPrivate());
        locationEntity.setCoordinate(geo);
        locationEntity.setCategory(fkCategoryEntity);
    }

    private static Point<G2D> createPointGeometry(LocationRequestBody location) {
        return Geometries.mkPoint(
                new G2D(location.coordinate().lon(),
                        location.coordinate().lat()), WGS84);
    }
}
