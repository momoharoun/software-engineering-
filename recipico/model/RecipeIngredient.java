package com.example.recipico.model;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "RecipeIngredient")
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId recipeIngredientId = new RecipeIngredientId();
    

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    @JsonBackReference // Backward reference
    private Recipe recipe;
    
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")
    private Ingredient ingredient;

    private double proportionValue;
    private String proportionUnit;

    

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, double proportionValue, String proportionUnit){
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.proportionValue = proportionValue;
        this.proportionUnit = proportionUnit;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return recipeIngredientId.equals(that.recipeIngredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeIngredientId);
    }
}
