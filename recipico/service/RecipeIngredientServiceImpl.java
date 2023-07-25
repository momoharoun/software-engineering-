package com.example.recipico.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipico.model.Ingredient;
import com.example.recipico.model.Recipe;
import com.example.recipico.model.RecipeIngredient;
import com.example.recipico.repository.RecipeIngredientRepository;
import com.example.recipico.repository.RecipeRepository;



@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService{

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<RecipeIngredient> getAllRecipesIngredients() {
        return recipeIngredientRepository.findAll();
    }

    @Override
    public RecipeIngredient addIngredientToRecipe(RecipeIngredient recipeIngredient) {
        final Ingredient ingredient = new Ingredient(recipeIngredient.getIngredient().getIngredientId());
        final Recipe recipe = new Recipe(recipeIngredient.getRecipe().getRecipeId());
        final String unit = recipeIngredient.getProportionUnit();
        final double quantity = recipeIngredient.getProportionValue();
        final RecipeIngredient recipeIngredientDetail = new RecipeIngredient(recipe, ingredient, quantity, unit);
        recipeIngredientRepository.save(recipeIngredientDetail);
        return recipeIngredientDetail; 
    }


}
