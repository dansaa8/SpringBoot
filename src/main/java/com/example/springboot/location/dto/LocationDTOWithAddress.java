package com.example.springboot.location.dto;



import com.example.springboot.category.CategoryDTO;
import com.example.springboot.location.entity.Location;
import com.example.springboot.serializer.Point2DSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.util.Map;

public record LocationDTOWithAddress(
        Integer id,
        String name,
        Boolean isPrivate,
        String description,
        String userID,
        @JsonSerialize(using = Point2DSerializer.class) Point<G2D> coordinate,
        CategoryDTO category,
        Map<String, Object> address
) {
    public LocationDTOWithAddress(Location location, Map<String, Object> address) {
        this(
                location.getId(),
                location.getName(),
                location.getIsPrivate(),
                location.getDescription(),
                location.getUserId(),
                location.getCoordinate(),
                new CategoryDTO(location.getCategory()),
                address
        );
    }
}
