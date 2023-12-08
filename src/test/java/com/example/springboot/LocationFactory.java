package com.example.springboot;

import com.example.springboot.category.Category;
import com.example.springboot.location.Location;
import com.example.springboot.location.LocationView;
import com.example.springboot.serializer.Point2DSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

public class LocationFactory {

    public static LocationView createLocationView(Location location) {
        Integer id = location.getId();
        String name = location.getName();
        Boolean isPrivate = location.getIsPrivate();
        String description = location.getDescription();
        String userId = location.getUserId();
        Point<G2D> coordinate = location.getCoordinate();

        Category category = location.getCategory();
        String categoryName = (category != null) ? category.getName() : null;
        String categorySymbol = (category != null) ? category.getSymbol() : null;
        String categoryDescription = (category != null) ? category.getDescription() : null;

        LocationView.CategoryView categoryView = new LocationView.CategoryView() {
            @Override
            public String getName() {
                return categoryName;
            }

            @Override
            public String getSymbol() {
                return categorySymbol;
            }

            @Override
            public String getDescription() {
                return categoryDescription;
            }
        };

        return new LocationView() {
            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Boolean getIsPrivate() {
                return isPrivate;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public String getUserId() {
                return userId;
            }

            @Override
            @JsonSerialize(using = Point2DSerializer.class)
            public Point<G2D> getCoordinate() {
                return coordinate;
            }

            @Override
            public LocationView.CategoryView getCategory() {
                return categoryView;
            }
        };
    }
}
