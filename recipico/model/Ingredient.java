package com.example.recipico.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Ingredient")
@Table(name = "ingredient")
public class Ingredient {
    
    // assigning ingred_id as a primary key for the ingredient
    // the ingred_id is generated automatically and incrementally when instantiating the Ingredient class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id") 
    private Long ingredientId;

    @Column(name = "ingredient_name", unique=true, nullable=false, length=50)  
    private String ingredientName;  
    
    @OneToMany(mappedBy = "ingredient",  cascade = CascadeType.ALL)
    private Set<RecipeIngredient> recipeIngredients = new HashSet<>();


    // Constructor - customized

    public Ingredient(Long id) {
        this.ingredientId = id;
    }
    public Ingredient(String name){
        
        this.ingredientName = name;
    }

    public Ingredient(Long id, String name){
        this.ingredientId = id;
        this.ingredientName = name;
    }

    //  to add/remove ingredients to recipes
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setIngredient(this);
    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setIngredient(null);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingredientName.equals(that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientName);
    }
}
