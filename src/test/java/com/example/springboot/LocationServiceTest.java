package com.example.springboot;

import com.example.springboot.category.Category;
import com.example.springboot.location.Location;
import com.example.springboot.location.LocationRepository;
import com.example.springboot.location.LocationService;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static com.example.springboot.LocationFactory.createLocationView;
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
        Category category = new Category();
        category.setId(2);
        category.setName("Theme park");
        category.setDescription("Carousels to ride");
        category.setSymbol("Locomotive");

        Location location1 = new Location();
        location1.setId(1);
        location1.setName("Liseberg");
        location1.setUserId("bertil");
        location1.setCategory(category);
        location1.setDescription("Göteborg");
        location1.setCoordinate(new Point<G2D>(new G2D(11.0, 11.0), WGS84));
        location1.setIsPrivate(false);

        Location location2 = new Location();
        location2.setId(2);
        location2.setName("Gröna Lund");
        location2.setUserId("ingrid");
        location2.setCategory(category);
        location2.setDescription("Stockholm");
        location2.setCoordinate(new Point<G2D>(new G2D(22.0, 22.0), WGS84));
        location1.setIsPrivate(false);

        var locView1 = createLocationView(location1);
        var locView2 = createLocationView(location2);

        Mockito.when(locationRepository.findByIsPrivateFalse()).thenReturn(
                List.of(locView1, locView2));

            var locations = service.getAllPublic();
            assertThat(locations).contains(locView1, locView2);
    }


}
