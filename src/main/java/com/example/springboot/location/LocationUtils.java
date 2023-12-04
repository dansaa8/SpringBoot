package com.example.springboot.location;

import com.example.springboot.category.Category;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

public class LocationUtils {

    private LocationUtils(){}

    public static void setLocationEntityProperties(
            Location locationEntity,
            Category fkCategoryEntity,
            LocationReqBody location) {

        var geo = createPointGeometry(location);

        locationEntity.setName(location.name());
        locationEntity.setDescription(location.description());
        locationEntity.setIsPrivate(location.isPrivate());
        locationEntity.setCoordinate(geo);
        locationEntity.setCategory(fkCategoryEntity);
    }

    private static Point<G2D> createPointGeometry(LocationReqBody location) {
        return Geometries.mkPoint(
                new G2D(location.coordinate().lon(),
                        location.coordinate().lat()), WGS84);
    }
}
