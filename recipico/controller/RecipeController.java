package com.example.recipico.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.recipico.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipico.model.Category;
import com.example.recipico.model.Difficulty;
import com.example.recipico.model.PdfExporter;
import com.example.recipico.model.Recipe;
import com.example.recipico.model.RecipeType;
import com.example.recipico.model.TextExporter;
import com.example.recipico.response.RecipeResponse;
import com.example.recipico.service.RecipeService;
import com.example.recipico.service.UserService;
import com.itextpdf.text.DocumentException;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @Autowired
    private final PdfExporter pdfExporter;

    @Autowired
    private final TextExporter textExporter;


    // Endpoint to Fetches all recipes 
    @GetMapping("/recipes")
    public List<RecipeResponse> findAllRecipes(@RequestHeader("userId") Long authorId ,
    @RequestParam(required = false) Category category,
    @RequestParam(required = false) String searchQuery,
    @RequestParam(required = false) RecipeType type,
    @RequestParam(required = false) Difficulty difficulty,
    @RequestParam(required = false) Integer prepTime) {
        final List<RecipeResponse> recipes = recipeService.findAllRecipes(searchQuery, category, type, difficulty, prepTime, authorId);

        if (recipes.isEmpty()) {
            // If no recipes are found, return an empty list
            return new ArrayList<>();
        } else {
            // If recipes are found, return the list of recipes
            return recipes;
        }
    }



    // Endpoint to add a new recipe
    @PostMapping("/recipes")
    public String addRecipe(@RequestHeader("userId") Long authorId, @RequestBody Recipe recipe){
        User author = userService.getUserById(authorId);
        recipe.setAuthor(author);
        System.out.println("Author added to recipe"+recipe.getAuthor().getEmail());
        recipeService.addRecipe(recipe);
        return "recipe added successfuly.";
    }


    // Endpoint to  update a recipe
    @PutMapping("/recipes/{recipeId}")
    public String updateRecipe(@RequestBody Recipe recipe, @PathVariable Long recipeId){
        recipeService.updateRecipe(recipe, recipeId);
        return "recipe updated successfully.";
    }


    // Endpoint tp delete a recipe
    @DeleteMapping("/recipes/{recipeId}")
    public String deleteRecipeById(@PathVariable Long recipeId){
        recipeService.deleteRecipeById(recipeId);
        return "recipe deleted successfully.";
        }


    // To get a specific recipe with its ID
    @GetMapping("/recipes/{recipeId}")
    public RecipeResponse getRecipeById(@PathVariable Long recipeId) {
        RecipeResponse recipe = recipeService.getRecipeById(recipeId);
        System.out.println("recipeId: " + recipeId + " : recipe: " + recipe);

        if (recipe == null) {
            // If no recipe is found, you can throw an exception or return null
            return null;
        } else {
            // If a recipe is found, return the recipe object
            return recipe;
        }
    }



    // To change the Proportions of a specific recipe using its ID
    @GetMapping("/recipes/{recipeId}/{proportionNewValue}")
    public RecipeResponse changeProportionsById(@PathVariable Long recipeId, @PathVariable Integer proportionNewValue) {
        RecipeResponse recipe = recipeService.changeProportionsById(recipeId, proportionNewValue);
        System.out.println("recipeId: " + recipeId + " : recipe: " + recipe);

        if (recipe == null) {
            // If no recipe is found, you can throw an exception or return null
            return null;
        } else {
            // If a recipe is found, return the recipe object with new values of proportions
            return recipe;
        }
    }


    // Endpoint to change the quantity type
    @PutMapping("/recipes/{recipeId}/changeMetric")
    public RecipeResponse changeMetric(@PathVariable("recipeId") Long recipeId,
                                                @RequestParam("metric") String metric) {
    RecipeResponse updatedMetric = recipeService.changeMetric(recipeId, metric);
    return updatedMetric;
    }



    // Endpoint to export a recipe to PDF
    @GetMapping("/recipes/{recipeId}/exportPdf")
    public String exportRecipeToPdf(@PathVariable Long recipeId) {
        try {
            Recipe recipe = recipeService.getRecipe(recipeId);
            String filePath = "exported_pdf_recipe.pdf";  // Provide the desired file path here
            pdfExporter.exportToPdf(recipe, filePath);
            return "Recipe exported to PDF successfully!";
        
        } catch (IOException | DocumentException e) {
            return "Error exporting recipe to PDF";
        }
    }


    //  Endpoint to export the ingredients of a recipe to a text file
    @GetMapping("/recipes/{recipeId}/exportText")
    public String exportRecipeToText(@PathVariable Long recipeId){
        try {
            Recipe recipe = recipeService.getRecipe(recipeId);
            String filePath = "exported_text_recipe.txt";  // Provide the desired file path here
            textExporter.exportToTextFile(recipe, filePath);
            return "Recipe exported to Text successfully!";

        } catch (IOException e) {
            return "Error exporting recipe to Text";
        }
    }

    // Endpoint to export the ingredients of many selected recipes to a text file
    @GetMapping("/recipes/exportText")
    public String exportRecipesToText(@RequestParam("recipeIds") List<Long> recipeIds){
        try {
        List<Recipe> recipes = recipeService.getRecipesByIds(recipeIds);
        System.out.println(("selected recipes are: " + recipes));
        String filePath = "exported_text_recipes.txt";  // Provide the desired file path here
        textExporter.exportRecipesToTextFile(recipes, filePath);
        return "Selected Recipes exported to Text successfully!";
        
        } catch (IOException e) {
        return "Error exporting the selected recipes to Text";
        }
    }
}
