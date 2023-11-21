package com.example.springboot;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    List<String> categories = List.of("Shop", "Restaurant", "Playground");

    List<String> getAllCategories() {
        return categories;
    }

    Optional<String> getOneCategory(int id) {
        if (id < 0 || id >= categories.size())
            return Optional.empty();
        return Optional.of(categories.get(id));
    }
}
