package com.example.springboot.location;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<LocationDTO> findLocationDTOAllBy();

//    List<LocationDTO> findLocationDTOAllByCategory(String category);


    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l WHERE l.id = :id")
    boolean existsById(@Param("id") Integer id);

    boolean existsLocationById(Integer id);

}


