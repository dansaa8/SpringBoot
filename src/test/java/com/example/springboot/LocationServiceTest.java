package com.example.springboot;

import com.example.springboot.category.Category;
import com.example.springboot.location.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

import static com.example.springboot.mocks.TestObjectFactory.*;
import static com.example.springboot.mocks.TestObjectFactory.createMockLocation1;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationService service;

    @Test
    void locationService() {
        Category fkCategory = createMockCategory();
        Location location1 = createMockLocation1(fkCategory);
        Location location2 = createMockLocation2(fkCategory);

        LocationDTO locationDTO1 = new LocationDTO(createMockLocation1(fkCategory));
        LocationDTO locationDTO2 = new LocationDTO(createMockLocation2(fkCategory));



        Mockito.when(locationRepository.findByIsPrivateFalse()).thenReturn(
                List.of(location1, location2));

            var expectedList = service.getAllPublic();
            assertThat(expectedList).contains(new LocationDTO(location1), new LocationDTO(location2));
    }

}
