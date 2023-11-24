package com.example.springboot.repository;

import com.example.springboot.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByStatus(String status);
}
