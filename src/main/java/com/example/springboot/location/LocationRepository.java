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

    List<LocationView> findAllBy();

    List<LocationView> findAllByCategoryId(int id);

    Optional<LocationView> findViewById(int id);

    @Query(value = """
            SELECT * FROM location
            WHERE ST_Distance_Sphere(coordinate, :coordinate) < :distance
                """, nativeQuery = true)
    List<Location> filterOnDistance(@Param("coordinate") Point<G2D> location, @Param("distance") double distance);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l WHERE l.id = :id")
    boolean existsById(@Param("id") int id);

    boolean existsLocationById(int id);

    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l WHERE l.name = :name AND l.id <> :excludedId")
    boolean existsByNameExcludingId(@Param("name") String name, @Param("excludedId") int excludedId);
}


