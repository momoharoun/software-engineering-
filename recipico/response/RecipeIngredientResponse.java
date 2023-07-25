package com.example.recipico.response;

import com.example.recipico.model.RecipeIngredient;

import lombok.Data;

@Data
public class RecipeIngredientResponse {
    
    private Long ingredientId;
    private String ingredientName;
    private double proportionValue;
    private String proportionUnit;

    public RecipeIngredientResponse(RecipeIngredient recipeIngredient) {
        this.ingredientId = recipeIngredient.getIngredient().getIngredientId();
        this.ingredientName = recipeIngredient.getIngredient().getIngredientName();
        this.proportionValue = recipeIngredient.getProportionValue();
        this.proportionUnit= recipeIngredient.getProportionUnit();
    }
}
