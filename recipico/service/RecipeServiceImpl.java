package com.example.recipico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipico.model.Category;
import com.example.recipico.model.Difficulty;
import com.example.recipico.model.Ingredient;
import com.example.recipico.model.Recipe;
import com.example.recipico.model.RecipeIngredient;
import com.example.recipico.model.RecipeType;
import com.example.recipico.repository.IngredientRepository;
import com.example.recipico.repository.RecipeRepository;
import com.example.recipico.response.RecipeCategoryResponse;
import com.example.recipico.response.RecipeIngredientResponse;
import com.example.recipico.response.RecipeResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    // @Autowired
    // private CategoryRepository categoryRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    // recipeResponse to be returned
    public RecipeResponse getRecipeById(Long recipeId) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            
            List<RecipeIngredientResponse> recipeIngredientResponses = recipe.getRecipeIngredients().stream()
            .map(RecipeIngredientResponse::new).toList();
            
            List <RecipeCategoryResponse> categories = recipe.getRecipeCategories().stream()
            .map(RecipeCategoryResponse::new).toList();

            // Create and return the RecipeResponse object based on the modified recipe
            RecipeResponse recipeResponse = new RecipeResponse(recipe, categories, recipeIngredientResponses);
            
            return recipeResponse;
        } else {
            // Handle the case when no recipe is found with the given ID
            throw new IllegalArgumentException("Recipe not found for ID: " + recipeId);
        }
    }


    @Override
    // recipe to be returned
    public Recipe getRecipe(Long recipeId){
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            return recipeOpt.get();
        } else {
            // Handle the case when no recipe is found with the given ID
            throw new IllegalArgumentException("Recipe to export not found for ID: " + recipeId);
        }
    }


    // to get 
    @Override
    public List<Recipe> getRecipesByIds(List<Long> recipeIds){
        // return recipeRepository.findAllById(recipeIds);
        List<Recipe> recipes = recipeRepository.findAllById(recipeIds);
        List<Recipe> existingRecipes = new ArrayList<>();
        for (Long recipeId : recipeIds) {
            boolean exists = false;
            
            for (Recipe recipe : recipes) {
                if (recipe.getRecipeId().equals(recipeId)) {
                    exists = true;
                    existingRecipes.add(recipe);
                    break;
                }
            }
            
            if (!exists) {
                System.out.println("Recipe with ID " + recipeId + " does not exist in the database.");
            }
        }
        
        return existingRecipes;
    }
    



    @Override
    public void addRecipe(Recipe recipe) {
        Logger log = LoggerFactory.getLogger(getClass());
        List<RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
        List<RecipeIngredient> newRecipeIngredients = new ArrayList<>(); // New list to hold new RecipeIngredients
        for (RecipeIngredient recipeIngredient : recipeIngredients) {
            final Ingredient ingredient;
            if (recipeIngredient.getIngredient().getIngredientId() != null) {
                ingredient = ingredientRepository.findById(recipeIngredient.getIngredient().getIngredientId())
                        .orElseThrow(() -> {
                            log.error("Ingredient not found.");
                            return new NoSuchElementException("Ingredient not found.");
                        });
            } else {
                String ingredientName = recipeIngredient.getIngredient().getIngredientName();
                Optional<Ingredient> existingIngredient = ingredientRepository.findByIngredientName(ingredientName);
                ingredient = existingIngredient
                        .orElseGet(() -> ingredientRepository.save(new Ingredient(ingredientName)));
            }

            final String unit = recipeIngredient.getProportionUnit();
            newRecipeIngredients
                    .add(new RecipeIngredient(recipe, ingredient, recipeIngredient.getProportionValue(), unit));
        }

        recipe.getRecipeIngredients().clear(); // Clear the original list
        recipe.getRecipeIngredients().addAll(newRecipeIngredients); // Add the new RecipeIngredients

        Recipe recipeDetail = recipeRepository.save(recipe);
        System.out.println("Recipe saved to the database with recipeId: " + recipeDetail.getRecipeId());
    }

    @Override
    public void deleteRecipeById(Long recipeId) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent())
            recipeRepository.deleteById(recipeId);
        else
            throw new RuntimeException("recipe not found.");
    }


    // to get all recipes
    @Override
    @Transactional(readOnly = true)
    public List<RecipeResponse> findAllRecipes(String searchQuery, Category category, RecipeType type,
            Difficulty difficulty, Integer preparationTime, Long authorId) {

        final List<RecipeResponse> recipes = recipeRepository.findAll()
                .stream()
                .filter(recipe -> {
                    // always filter by authorId
                    if (authorId != null && recipe.getAuthor().getId() != authorId) {
                        return false; // Filter out recipes that do not belong to the specified author
                    }
                    if (searchQuery != null && !((recipe.getRecipeName()).toLowerCase()).contains((searchQuery).toLowerCase())) {
                        return false; // Filter out recipes that do not contain the search query in their name
                    }
                    if (difficulty != null && recipe.getDifficulty() != difficulty) {
                        return false; // Filter out recipes with a different difficulty
                    }
                    if (category != null && !recipe.getRecipeCategories().contains(category)) {
                    return false; // Filter out recipes that do not belong to the specified category
                    }
                    if (type != null && recipe.getRecipeType() != type) {
                    return false; // Filter out recipes with a different type
                    }
                    if (preparationTime != null && recipe.getPrepTime() > preparationTime) {
                        return false; // Filter out recipes with a longer preparation time
                    }
                    return true; // Include recipes that pass all the filters
                })
                .map(recipe -> new RecipeResponse(
                        recipe,
                        recipe.getRecipeCategories().stream().map(RecipeCategoryResponse::new).toList(),
                        recipe.getRecipeIngredients().stream().map(RecipeIngredientResponse::new).toList()))
                .collect(Collectors.toList());

        return recipes;
    }


    // to change the proportions 
    @Override
    @Transactional
    public RecipeResponse changeProportionsById(Long recipeId, Integer proportionMultiplier) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            List<RecipeIngredient> ingredients = recipe.getRecipeIngredients();
            
            // Multiply the proportions of each ingredient by the proportionMultiplier
            for (RecipeIngredient ingredient : ingredients) {
                double newProportion = ingredient.getProportionValue() * proportionMultiplier;
                ingredient.setProportionValue(newProportion);
            }
            
            List<RecipeIngredientResponse> recipeIngredientResponses = ingredients.stream()
            .map(RecipeIngredientResponse::new).toList();
            
            List <RecipeCategoryResponse> categories = recipe.getRecipeCategories().stream()
            .map(RecipeCategoryResponse::new).toList();

            // Create and return the RecipeResponse object based on the modified recipe
            RecipeResponse recipeResponse = new RecipeResponse(recipe, categories, recipeIngredientResponses);
            
            return recipeResponse;
        } else {
            // Handle the case when no recipe is found with the given ID
            throw new IllegalArgumentException("Recipe not found for ID: " + recipeId);
        }
    }


    // to change the metrics
    @Override
    @Transactional
    public RecipeResponse changeMetric(Long recipeId, String metric){
        
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            List<RecipeIngredient> ingredients = recipe.getRecipeIngredients();
            
            // change the metric
            for (RecipeIngredient ingredient : ingredients) {
                String currentUnit = ingredient.getProportionUnit();
                double currentValue = ingredient.getProportionValue();
                
            }
        }

        return null;
    }

    @Override
    public void updateRecipe(Recipe recipe, Long recipeId) {
        Optional<Recipe> recipeDetailOpt = recipeRepository.findById(recipeId);
        if (recipeDetailOpt.isPresent()) {
            Recipe recipeDetail = recipeDetailOpt.get();
            if (recipe.getRecipeName() != null || recipe.getRecipeName().isEmpty())
                recipeDetail.setRecipeName((recipe.getRecipeName()));
            if (recipe.getRecipeDescription() != null || recipe.getRecipeDescription().isEmpty())
                recipeDetail.setRecipeDescription(recipe.getRecipeDescription());
            if (recipe.getPrepTime() != null)
                recipeDetail.setPrepTime(recipe.getPrepTime());
            if (recipe.getImage_link() != null || recipe.getImage_link().isEmpty())
                recipeDetail.setImage_link(recipe.getImage_link());
            recipeRepository.save(recipeDetail);
        } else {
            throw new RuntimeException("recipe not found.");
        }
    }
}
