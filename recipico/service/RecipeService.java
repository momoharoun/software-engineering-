package com.example.recipico.service;

import java.util.List;

import com.example.recipico.model.Category;
import com.example.recipico.model.Difficulty;
import com.example.recipico.model.Recipe;
import com.example.recipico.model.RecipeType;
import com.example.recipico.response.RecipeResponse;

public interface RecipeService {

    RecipeResponse getRecipeById(Long recipeId);

    Recipe getRecipe(Long recipeId);

    void addRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe, Long recipeId);

    void deleteRecipeById(Long recipeId);

    List<RecipeResponse> findAllRecipes(String searchQuery, Category category, RecipeType type, Difficulty difficulty,
            Integer prepTime, Long authorId);

    RecipeResponse changeProportionsById(Long recipeId, Integer proportionNewValue);

    RecipeResponse changeMetric(Long recipeId, String metric);

    List<Recipe> getRecipesByIds(List<Long> recipeIds);
}
