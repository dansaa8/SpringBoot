package com.example.springboot;

import com.example.springboot.category.Category;
import com.example.springboot.location.Location;
import com.example.springboot.location.LocationRepository;
import com.example.springboot.location.LocationService;
import com.example.springboot.location.LocationView;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationService service;

    @Test
    void locationService() {

        LocationView locationView1 = new LocationView() {
            @Override
            public Integer getId() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public Boolean getIsPrivate() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public Point<G2D> getCoordinate() {
                return null;
            }

            @Override
            public CategoryView getCategory() {
                return null;
            }
        }

        Mockito.when(locationRepository.findByIsPrivateFalse()).thenReturn(
                List.of(locationView1, locationView2));

            var locations = service.getAllPublic();
            assertThat(locations).contains(locationView1, locationView2);
    }

    public static Location createMockLocation1(Category category) {
        Location location = new Location();
        location.setId(1);
        location.setName("Liseberg");
        location.setUserId("bertil");
        location.setCategory(category);
        location.setDescription("Göteborg");
        location.setCoordinate(new Point<G2D>(new G2D(11.0, 11.0), WGS84));
        location.setIsPrivate(false);
        return location;
    }

}
