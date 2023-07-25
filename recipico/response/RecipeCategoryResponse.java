package com.example.recipico.response;

import com.example.recipico.model.Category;
import com.example.recipico.model.RecipeCategory;

import lombok.Data;


@Data
public class RecipeCategoryResponse {

    // private Long categoryId;
    private RecipeCategory categoryName;

    
    public RecipeCategoryResponse(Category category) {
        // this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
    }
}


