package com.example.recipico.service;

import java.util.List;

import com.example.recipico.model.Category;


public interface CategoryService {
    List<Category> getAllCategory();

    Category getCategoryById(Long CategoryId);

    void addCategory(Category category);

    void deleteCategoryyId(Long CategoryId);
}
