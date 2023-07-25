package com.example.recipico.model;


import java.util.ArrayList;

import java.util.List;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe {
    
    // assigning recipeId as a primary key for the ingredient
    // the recipeId is generated automatically and incrementally when instantiating the Recipe class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id", updatable = false)
    private Long recipeId;

    @Column(name = "name", nullable = false, unique=true, length=100)
    private String recipeName;

    @Column(name = "description", nullable = false)
    private String recipeDescription;
    
    @Column
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(value = EnumType.STRING)
    private RecipeType recipeType;

    @Column(name = "prep_time")
    private Integer prepTime;

    @Column(name = "cook_time")
    private Integer cookTime;

    private Integer servings;

    private String image_link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id", updatable = false, nullable = false)
    private User author;

    // to create a new table of the primary key of both entities: recipes and ingredients
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonManagedReference // Forward reference
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    
    
    // to create a new table of the primary key of both entities: recipes and categories
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_categories", 
    joinColumns = @JoinColumn (name= "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> recipeCategories = new ArrayList<>();


    // Constructors - customized
    public Recipe(Long id){
        this.recipeId = id;
    }

    public Recipe(Long id, String name, String description, Integer prepTime, Integer cookTime,
                Integer servings,  Difficulty difficulty, RecipeType recipeType, String img) {
        this.recipeId = id;
        this.recipeName = name;
        this.recipeDescription = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.difficulty = difficulty;
        this.recipeType = recipeType;
        this.image_link = img;
    }


    // to add/remove ingredients to/from recipes
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setRecipe(null);
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return getRecipeId() != null &&
                Objects.equals(getRecipeId(), recipe.getRecipeId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
