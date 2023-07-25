package com.example.recipico.model;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PdfExporter {
    public void exportToPdf(Recipe recipe, String filePath) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        
        // Add recipe details to the document
        document.add(new Paragraph("Recipe: " + recipe.getRecipeName()));
        document.add(new Paragraph("Recipe Type: " + recipe.getRecipeType()));
        document.add(new Paragraph("Recipe Difficulty: " + recipe.getDifficulty()));
        document.add(new Paragraph("Servings: " + recipe.getServings()));
        document.add(new Paragraph("Preparation Time: " + recipe.getPrepTime()));
        document.add(new Paragraph("Cooking Time: " + recipe.getCookTime()));

        document.add(new Paragraph("Instructions: " + recipe.getRecipeDescription()));
        
        // Add recipe ingredients to the document
        document.add(new Paragraph("Recipe Ingredients:"));
        List<RecipeIngredient> ingredients = recipe.getRecipeIngredients();
        for (RecipeIngredient ingredient : ingredients) {
            String ingredientText = "- " + ingredient.getIngredient().getIngredientName() + ": " + ingredient.getProportionValue()  + ingredient.getProportionUnit();
            document.add(new Paragraph(ingredientText));
        }
        
        document.close();
    }
}
