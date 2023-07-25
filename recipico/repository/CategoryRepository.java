package com.example.recipico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipico.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
