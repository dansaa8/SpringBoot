package com.example.springboot.mocks;

import com.example.springboot.category.Category;
import com.example.springboot.location.Location;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

public class TestObjectFactory {

    public static Category createMockCategory() {
        Category category = new Category();
        category.setId(2);
        category.setName("Theme park");
        category.setDescription("Carousels to ride");
        category.setSymbol("Locomotive");
        return category;
    }

    public static Location createMockLocation1(Category category) {
        Location location = new Location();
        location.setId(1);
        location.setName("Liseberg");
        location.setUserId("bertil");
        location.setCategory(category);
        location.setDescription("Göteborg");
        location.setCoordinate(new Point<G2D>(new G2D(11.0, 11.0), WGS84));
        location.setIsPrivate(false);
        return location;
    }

    public static Location createMockLocation2(Category category) {
        Location location = new Location();
        location.setId(2);
        location.setName("Gröna Lund");
        location.setUserId("ingrid");
        location.setCategory(category);
        location.setDescription("Stockholm");
        location.setCoordinate(new Point<G2D>(new G2D(22.0, 22.0), WGS84));
        location.setIsPrivate(false);
        return location;
    }
}
