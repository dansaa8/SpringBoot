package com.example.springboot.dto;

import com.example.springboot.entity.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

public record LocationDto(
        Integer id,
        @NotNull @Size(max = 255) String name,
        String status,
        @Size(max = 255) String description,
        Instant createdAt,
        Instant lastModified,
        CategoryDto category) implements Serializable {

        public LocationDto(Location location) {
                this(   location.getId(),
                        location.getName(),
                        location.getStatus(),
                        location.getDescription(),
                        location.getCreatedAt(),
                        location.getLastModified(),
                        new CategoryDto(location.getFkCategory()));
        }
}