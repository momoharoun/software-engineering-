package com.example.recipico.service;

import java.util.List;

import com.example.recipico.model.Ingredient;

public interface IngredientService {

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientById(Long ingredientId);

    void addIngredient(Ingredient ingredient);

    void deleteIngredientyId(Long ingredientId);
    
}
