package com.example.springboot.dto;

import com.example.springboot.Point2DSerializer;
import com.example.springboot.dto.CategoryDto;
import com.example.springboot.entity.Location;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.geolatte.geom.G2D;

import java.awt.*;
import java.io.Serializable;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

/**
 * DTO for {@link com.example.springboot.entity.Location}
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