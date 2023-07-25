package com.example.recipico.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeCategoryRequest{

    private Long recipeId;

    private Long categoryId;

    private String categoryName;
}
