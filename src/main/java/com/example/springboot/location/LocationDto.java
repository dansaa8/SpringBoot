package com.example.springboot.location;

import com.example.springboot.Point2DSerializer;
import com.example.springboot.category.CategoryDto;
import com.example.springboot.location.Location;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.geolatte.geom.G2D;

import java.io.Serializable;

import org.geolatte.geom.Point;

/**
 * DTO for {@link Location}
 */
public record LocationDto(
        Integer id,
        @NotNull @Size(max = 255) String name,
        @NotNull Boolean isPrivate,
        @Size(max = 255) String description,
        @JsonSerialize(using = Point2DSerializer.class) Point<G2D> coordinate,
        CategoryDto category)
        implements Serializable {

    public LocationDto(Location location) {
        this(
                location.getId(),
                location.getName(),
                location.getIsPrivate(),
                location.getDescription(),
                location.getCoordinate(),
                new CategoryDto(location.getCategory())
        );
    }
}