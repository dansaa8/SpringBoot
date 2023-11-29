package com.example.springboot.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    Optional<Category> findByNameIgnoreCase(String name);

    Optional<CategoryDTO> findCategoryDTOById(Integer id);

    List<CategoryDTO> findCategoryDTOAllBy();

    boolean existsByName(String name);
}
