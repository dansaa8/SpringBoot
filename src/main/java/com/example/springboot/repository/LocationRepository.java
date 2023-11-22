package com.example.springboot.repository;

import com.example.springboot.entity.Location;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends ListCrudRepository<Location, Integer> {
}
