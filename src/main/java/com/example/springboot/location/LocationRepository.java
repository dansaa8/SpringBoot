package com.example.springboot.location;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {

    @Query("SELECT l FROM Location l WHERE l.userId = ?#{principal?.username} OR l.isPrivate = FALSE")
    List<LocationView> findAllBy();

    @Query("")
    List<LocationView> findAllByCategoryId(int id);

    @Query("SELECT l FROM Location l WHERE (l.userId = ?#{principal?.username} OR l.isPrivate = FALSE) AND l.id = :id")
    Optional<LocationView> findViewById(@Param("id") int id);

    boolean existsByName(String name);


    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l WHERE l.name = :name AND l.id <> :excludedId")
    boolean existsByNameExcludingId(@Param("name") String name, @Param("excludedId") int excludedId);
}


