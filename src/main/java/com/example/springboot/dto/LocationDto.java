package com.example.springboot.dto;

import com.example.springboot.dto.CategoryDto;
import com.example.springboot.entity.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springboot.entity.Location}
 */
public record LocationDto(Integer id, @NotNull @Size(max = 255) String name, @NotNull Boolean isPrivate,
                          @Size(max = 255) String description, CategoryDto category) implements Serializable {

    public LocationDto(Location location) {
        this(
                location.getId(),
                location.getName(),
                location.getIsPrivate(),
                location.getDescription(),
                new CategoryDto(location.getCategory())
        );
    }
}