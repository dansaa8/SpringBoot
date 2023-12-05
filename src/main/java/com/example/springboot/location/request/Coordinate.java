package com.example.springboot.location.request;

import com.example.springboot.constraint.coordinate.Latitude;
import com.example.springboot.constraint.coordinate.Longitude;

public record Coordinate(@Latitude double lat, @Longitude double lon) {
}
