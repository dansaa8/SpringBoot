package com.example.springboot.location;

import com.example.springboot.category.CategoryDTO;
import com.example.springboot.serializer.Point2DSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

public record LocationDTO(
        Integer id,
        String name,
        Boolean isPrivate,
        String description,
        String userID,
        @JsonSerialize(using = Point2DSerializer.class) Point<G2D> coordinate,
        CategoryDTO category
) {
    public LocationDTO(Location location) {
        this(
                location.getId(),
                location.getName(),
                location.getIsPrivate(),
                location.getDescription(),
                location.getUserId(),
                location.getCoordinate(),
                new CategoryDTO(location.getCategory())
        );
    }
}
