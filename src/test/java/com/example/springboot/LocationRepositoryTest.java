package com.example.springboot;

import com.example.springboot.category.Category;
import com.example.springboot.category.CategoryRepository;
import com.example.springboot.location.Location;
import com.example.springboot.location.LocationRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=none"
})
public class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CategoryRepository categoryRepository;

    void saveNewLocation() {
        Category category = categoryRepository.findByNameIgnoreCase("Theme park").get();

        Location location = new Location();
        location.setName("Test");
        location.setDescription("Some description");
        location.setIsPrivate(false);
        location.setCategory(category);
        location.setUserId("bertil");
        location.setCoordinate(new Point<G2D>(new G2D(11.0, 11.0), WGS84));
        var sLocation = locationRepository.save(location);

        assertThat(locationRepository.findById(sLocation.getId()))
                .isNotEmpty()
                .get().isEqualTo(sLocation);
    }


}
