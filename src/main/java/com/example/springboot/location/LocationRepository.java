package com.example.springboot.location;

import com.example.springboot.location.Location;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByIsPrivateFalse();
    List<Location> findByIsPrivateFalseAndCategoryName(String category);
}
