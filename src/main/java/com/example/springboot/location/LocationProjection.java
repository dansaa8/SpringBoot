package com.example.springboot.location;

import com.example.springboot.Point2DSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;


public interface LocationProjection {
   String getIdName();
   Integer getId();
   String getName();
//   CategoryProjection getCategory();
   boolean getIsPrivate();
   String getDescription();
   @JsonSerialize(using = Point2DSerializer.class) Point<G2D> getCoordinate();
}