package com.example.recipico.request;

import java.util.List;

import com.example.recipico.model.Difficulty;
import com.example.recipico.model.RecipeType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RecipeRequest {
    private Long recipeId;


    @NotBlank
    @Size(min = 3, max = 50)
    private String recipeName;

    @NotBlank
    private String recipeDescription;


    private Integer prepTime;
    private Integer cookTime;

    @NotBlank
    private String image_link;

    @Min(1)
    @Max(12)
    private Integer servings;


    @NotNull
    private Difficulty difficulty;

    @NotNull
    private RecipeType recipeType;

    private List<RecipeIngredientRequest> recipeIngredients;
    private List <RecipeCategoryRequest>  recipeCategories;
}
