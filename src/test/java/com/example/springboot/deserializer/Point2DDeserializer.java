package com.example.springboot.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

import java.io.IOException;

public class Point2DDeserializer extends StdDeserializer<Point<G2D>> {

    public Point2DDeserializer() {
        this(null);
    }

    public Point2DDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Point<G2D> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        double latitude = node.get("lat").asDouble();
        double longitude = node.get("long").asDouble();

        // Create G2D object
        G2D coordinates = new G2D(longitude, latitude);

        // Create Point<G2D> with a default CRS (WGS84)
        return new Point<>(coordinates, CoordinateReferenceSystems.WGS84);
    }
}
