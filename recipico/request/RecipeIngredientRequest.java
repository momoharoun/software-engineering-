package com.example.recipico.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeIngredientRequest {

    private Long recipeId;

    private Long ingredientId;

    private String ingredientName;

    @NotNull
    private double proportionValue;
    
    @NotNull
    private String proportionUnit;
}