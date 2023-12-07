package com.example.springboot;

import com.example.springboot.category.Category;
import com.example.springboot.category.CategoryRepository;
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

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;

    @Mock
    CategoryRepository categoryRepository;

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

        Location location2 = new Location();
        location2.setId(2);
        location2.setName("Gröna Lund");
        location2.setUserId("ingrid");
        location2.setCategory(category);

        Mockito.when(locationRepository.findAll()).thenReturn(List.of(location1, location2));

        var locations = service.getAllPublic();

        assertThat(locations).containsSequence(new LocationView() {
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
        }) {

        })
    }


}
