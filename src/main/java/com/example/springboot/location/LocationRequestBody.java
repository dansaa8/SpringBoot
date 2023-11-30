package com.example.springboot.location;

import com.example.springboot.constraint.CategoryMustExist;
import com.example.springboot.constraint.CoordinateOkValues;
import jakarta.validation.constraints.NotNull;

public record LocationRequestBody(
        @NotNull String name,
        @NotNull boolean isPrivate,
        @NotNull String description,
        @NotNull @CategoryMustExist String categoryName,
        @NotNull @CoordinateOkValues Coordinate coordinate
        ) {
}
