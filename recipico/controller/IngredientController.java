package com.example.recipico.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.recipico.model.Ingredient;
import com.example.recipico.service.IngredientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class IngredientController {
    
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/ingredients")
    public List<Ingredient> getAllingredient(){
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients.isEmpty()) {
            // If no ingredients are found, return an empty list
            return null;
        } else {
            // If ingredients are found, return the list of ingredients
            return ingredients;
        }
    }
    
    @GetMapping("/ingredients/{ingredientId}")
    public Ingredient getingredientById(@PathVariable Long ingredientId){
        Ingredient ingredient = ingredientService.getIngredientById(ingredientId);
        System.out.println("ingredientId : "+ingredientId+" : ingredient : "+ingredient);
        return ingredient;
    }

    @PostMapping("/ingredients")
    public String addingredient(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
        return "ingredient saved successfuly.";
    }


    @DeleteMapping("/ingredients/{ingredientId}")
    public String deleteingredientyId(@PathVariable Long ingredientId){
        ingredientService.deleteIngredientyId(ingredientId);
        return "ingredient deleted successfully.";
    }
}
