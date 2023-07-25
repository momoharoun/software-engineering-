package com.example.recipico.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class TextExporter {
    public void exportToTextFile(Recipe recipe, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write recipe details
            writer.write("Recipe: " + recipe.getRecipeName());
            writer.newLine();
            writer.write("Recipe Ingredients:");
            writer.newLine();

            List<RecipeIngredient> ingredients = recipe.getRecipeIngredients();
            for (RecipeIngredient ingredient : ingredients) {
                writer.write("- " + ingredient.getIngredient().getIngredientName() + ": " + ingredient.getProportionValue()  + ingredient.getProportionUnit());
                writer.newLine();
            }
        }
    }


    public void exportRecipesToTextFile(List<Recipe> recipes, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Recipe recipe : recipes) {
                writer.write("Recipe: " + recipe.getRecipeName());
                writer.newLine();
                writer.write("Recipe Ingredients:");
                writer.newLine();
                List<RecipeIngredient> ingredients = recipe.getRecipeIngredients();
                for (RecipeIngredient ingredient : ingredients) {
                    writer.write("- " + ingredient.getIngredient().getIngredientName() + ": " + ingredient.getProportionValue()  + ingredient.getProportionUnit());
                    writer.newLine();
                }
                writer.newLine();
                writer.newLine();
            }
        }
    }
}