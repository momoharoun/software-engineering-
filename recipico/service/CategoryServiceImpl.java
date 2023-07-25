package com.example.recipico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipico.model.Category;
import com.example.recipico.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long CategoryId) {
        Optional<Category> CategoryOpt = categoryRepository.findById(CategoryId);
        if(CategoryOpt.isPresent())
            return CategoryOpt.get();
        else
            throw new RuntimeException("Category not found.");
    }

    @Override
    public void addCategory(Category Category) {
        Category CategoryDetail = categoryRepository.save(Category);
        System.out.println("Category saved to db with CategoryId : " + CategoryDetail.getCategoryId());
    }


    @Override
    public void deleteCategoryyId(Long CategoryId) {
        Optional<Category> CategoryOpt = categoryRepository.findById(CategoryId);
        if(CategoryOpt.isPresent())
            categoryRepository.deleteById(CategoryId);
        else
            throw new RuntimeException("Category not found.");
    }
    
}
