package com.example.springboot.location;

import com.example.springboot.constraint.category.CategoryMustExist;
import com.example.springboot.constraint.coordinate.CoordinateOkValues;
import jakarta.validation.constraints.NotNull;

public record LocationReqBody(
        @NotNull String name,
        @NotNull boolean isPrivate,
        @NotNull String description,
        @NotNull @CategoryMustExist String categoryName,
        @NotNull @CoordinateOkValues Coordinate coordinate
        ) {
}
