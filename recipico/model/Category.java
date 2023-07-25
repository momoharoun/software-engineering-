package com.example.recipico.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name="Category")
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RecipeCategory categoryName;
    //private String categoryName;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "recipeCategories")
    private Set<Recipe> recipes = new HashSet<>();
    
    // Constructors
    public Category(RecipeCategory categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Long id, RecipeCategory name){
        this.categoryId = id;
        this.categoryName = name;
    }


    // to add/remove categories to recipes
    public void addRecipeCategory(Recipe recipe) {
        recipes.add(recipe);
        recipe.getRecipeCategories().add(this);
    }
    
    public void removeRecipeCategory(Recipe recipe) {
        recipes.remove(recipe);
        recipe.getRecipeCategories().remove(this);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }
    
}
