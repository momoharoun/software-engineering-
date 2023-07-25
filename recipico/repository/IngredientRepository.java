package com.example.recipico.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipico.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

    Optional<Ingredient> findByIngredientName(String ingredientName);
    
}
