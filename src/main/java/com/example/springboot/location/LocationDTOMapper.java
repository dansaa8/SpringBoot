package com.example.springboot.location;

import com.example.springboot.category.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class LocationDTOMapper implements Function<Location, LocationDTO> {

    @Override
    public LocationDTO apply(Location location) {
        return new LocationDTO(
                location.getId(),
                location.getName(),
                location.getIsPrivate(),
                location.getDescription(),
                location.getUserId(),
                location.getCoordinate(),
                new CategoryDTO(
                        location.getCategory().getName(),
                        location.getCategory().getSymbol(),
                        location.getCategory().getDescription()
                )
        );
    }

}
