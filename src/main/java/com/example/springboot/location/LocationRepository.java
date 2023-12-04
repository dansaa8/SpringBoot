package com.example.springboot.location;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {

    // PUBLIC
    List<LocationView> findByIsPrivateFalse();
    Optional<LocationView> findByIsPrivateFalseAndAndId(@Param("id") int id);
    List<LocationView> findAllByIsPrivateFalseAndCategory_Name(@Param("name")String name);


    @Query("SELECT l FROM Location l WHERE l.userId = :id")
    List<LocationView> findMyLocations(@Param("id") String id);


    @Query("SELECT l FROM Location l WHERE (l.userId = ?#{principal?.username} OR l.isPrivate = FALSE) AND l.id = :id")
    Optional<LocationView> findViewById(@Param("id") int id);


    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l " +
            "WHERE l.name = :name AND l.id <> :excludedId")
    boolean existsByNameExcludingId(@Param("name") String name, @Param("excludedId") int excludedId);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Location l " +
            "WHERE l.id = :id AND l.userId = :userId")
    boolean isUserOwningEntity(@Param("id") int id, @Param("userId") String userId);

    boolean existsByName(String name);



}


