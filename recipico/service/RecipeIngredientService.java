package com.example.recipico.service;

import java.util.List;

import com.example.recipico.model.RecipeIngredient;

public interface RecipeIngredientService {

    List<RecipeIngredient> getAllRecipesIngredients();

    RecipeIngredient addIngredientToRecipe(RecipeIngredient recipeIngredient);

    //void updateRecipeIngredients(RecipeIngredient recipeIngredient, Long recipeId);
    //void changeIngredientsProportion(Long RecipeId, Short Proportion);
    
}
