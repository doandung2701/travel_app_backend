package com.travelapp.service;

import com.travelapp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();


    Optional<Category> findOne(Long id);

    void delete(Long id);
}
