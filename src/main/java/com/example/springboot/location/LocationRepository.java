package com.example.springboot.location;

import com.example.springboot.location.Location;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByIsPrivateFalse();
    List<Location> findByIsPrivateFalseAndCategoryName(String category);

    Optional<Location> findByNameIgnoreCase(@Size(max = 255) @NotNull String name);
}
