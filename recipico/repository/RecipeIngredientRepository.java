package com.example.recipico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipico.model.RecipeIngredient;
import com.example.recipico.model.RecipeIngredientId;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId>  {
    
}
