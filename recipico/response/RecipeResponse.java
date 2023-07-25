package com.example.recipico.response;

import java.util.List;

import com.example.recipico.model.Recipe;


import lombok.Data;

@Data
public class RecipeResponse {

    private Long recipeId;
    private String recipeName;
    private String recipeDescription;
    private Integer prepTime;
    private Integer cookTime;
    private String image_link;
    private Integer servings;
    private String difficulty;
    private String recipeType;
    private List<RecipeCategoryResponse> categories;
    private List<RecipeIngredientResponse> ingredients;

    

    public RecipeResponse(Recipe recipe,  List<RecipeCategoryResponse> recipeCategories, List<RecipeIngredientResponse> ingredients) {
        this.recipeId = recipe.getRecipeId();
        this.recipeName = recipe.getRecipeName();
        this.recipeDescription = recipe.getRecipeDescription();
        this.prepTime = recipe.getPrepTime();
        this.cookTime = recipe.getCookTime();
        this.servings = recipe.getServings();
        this.image_link = recipe.getImage_link();
        this.difficulty = recipe.getDifficulty().getLabel();
        this.recipeType = recipe.getRecipeType().getType();
        this.categories = recipeCategories;
        this.ingredients = ingredients;
    }
}
