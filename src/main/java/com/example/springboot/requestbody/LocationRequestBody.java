package com.example.springboot.requestbody;

import java.awt.*;

public record LocationRequestBody(
        String name,
        boolean isPrivate,
        Point coordinate,
        String description,
        String categoryName
        ) {
}
