package com.example.recipico.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecipeCategory {
    APPETIZER_AND_SNACK("Appetizer and Snack"),
    MAIN_DISH("Main Dish"),
    SIDE_DISH("Side Dish"),
    EVERYDAY_COOKING("Everyday Cooking"),
    FRUITS_AND_VEGETABLES("Fruits and Vegetables"),
    SEAFOOD("Seafood"),
    MEAT_AND_POULTRY("Meat and Poultry"),
    WORLD_CUISINE("World Cuisine"),
    PASTA_AND_NOODLES("Pasta and Noodles"),
    DESSERT("Dessert"),
    DRINKS("Drinks"),
    BREAKFAST_AND_BRUNCH("Breakfast and Brunch"),
    SALAD("Salad"),
    SOUP("Soup Stews and Chili"),
    BREAD("Bread");

    private final String categoryName;
    
    public String getCategoryName() {
        return categoryName;
    }

}

