package com.example.springboot.location;

import com.example.springboot.Point2DSerializer;
import com.example.springboot.category.CategoryDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.geolatte.geom.G2D;

import java.io.Serializable;

import org.geolatte.geom.Point;
import org.hibernate.annotations.Formula;

/**
 * DTO for {@link Location}
 */
public record LocationDTO(
        Integer id,
        String name,
        Boolean isPrivate,
        String description,
        @JsonSerialize(using = Point2DSerializer.class) Point<G2D> coordinate,
        CategoryDTO category
) implements Serializable{
        public LocationDTO(Location l) {
                this(l.getId(), l.getName(), l.getIsPrivate(), l.getDescription(), l.getCoordinate(),
                        new CategoryDTO(l.getCategory()));
        }
}
