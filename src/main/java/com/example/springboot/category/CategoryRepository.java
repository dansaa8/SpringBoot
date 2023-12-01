package com.example.springboot.category;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    Optional<Category> findByNameIgnoreCase(String name);

    Optional<CategoryView> findViewById(Integer id);


//    @EntityGraph(attributePaths = {"places"})
////    @Query("""
////            select c from Category c join c.locations l where l.id = :id and l.isPrivate = true
////            """)
//    List<Category>findAllBy(String name);

    List<CategoryView> findCategoryDTOAllBy();

    boolean existsByNameIgnoreCase(String name);
}
