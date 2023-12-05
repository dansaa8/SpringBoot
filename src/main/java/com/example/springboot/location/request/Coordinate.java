package com.example.springboot.location.request;

import com.example.springboot.constraint.coordinate.CoordinateOkValues;

@CoordinateOkValues
public record Coordinate(double lat, double lon) {
}
