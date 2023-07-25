package com.example.recipico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipico.model.RecipeIngredient;
import com.example.recipico.service.RecipeIngredientService;







@RestController
public class RecipeIngredientController {
    

    @Autowired
    private RecipeIngredientService recipeIngredientService ;


    @GetMapping("/recipes/getAllIngredients")
    public List<RecipeIngredient> getAllRecipesIngredients(){
        List<RecipeIngredient> recipesiIngredients = recipeIngredientService.getAllRecipesIngredients();
        System.out.println("recipes ingredients : "+recipesiIngredients);
        return recipesiIngredients;
    }


    @PostMapping("/recipes/addIngredients")
    public String addIngredientToRecipe(@RequestBody RecipeIngredient recipeIngredient){
        RecipeIngredient recipesiIngredients = recipeIngredientService.addIngredientToRecipe(recipeIngredient);
        System.out.println("recipes ingredients added successfully : "+recipesiIngredients);
        return "ingredients added successfuly to the recipe";
    }
}
