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

    List<Location> findByIsPrivateFalse();
    Optional<Location> findByIsPrivateFalseAndId(@Param("id") int id);
    List<Location> findAllByIsPrivateFalseAndCategory_Name(@Param("name")String name);


    @Query("SELECT l FROM Location l WHERE l.userId = :id")
    List<Location> findMyLocations(@Param("id") String id);

    @Query("SELECT l FROM Location l WHERE l.userId = :userId AND l.id = :id")
    Optional<Location> findLocationByUserIdAndId(@Param("userId") String userId, @Param("id") int id);

    @Query("SELECT l FROM Location l " +
            "WHERE FUNCTION('ST_Distance_Sphere', l.coordinate, :coordinate) < :distance " +
            "AND l.isPrivate = false")
    List<Location> filterOnDistance(Point<G2D> coordinate, double distance);
}


