package com.example.springboot.category;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    Optional<Category> findByNameIgnoreCase(String name);

    Optional<CategoryView> findViewById(Integer id);

    List<CategoryView> findAllBy();

    boolean existsByNameIgnoreCase(String name);
}
