package com.example.springboot.location;

import com.example.springboot.Point2DSerializer;
import com.example.springboot.category.Category;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

public interface LocationView {
    Integer getId();
    String getName();
    Boolean getIsPrivate();
    String getDescription();

    @JsonSerialize(using = Point2DSerializer.class)
    Point<G2D> getCoordinate();

    CategoryView getCategory();
    interface CategoryView {
        String getName();
        String getSymbol();
        String getDescription();
    }
}