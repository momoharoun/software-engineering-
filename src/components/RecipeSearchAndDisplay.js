// This is the parent component for the recipe search and display grid.

import React, { useState } from "react";
import SearchBar from "./RecipeSearchBar";
import RecipeGrid from "./RecipeGrid";

function RecipeSearchAndDisplay() {
    const [recipeData, setRecipeData] = useState([]);

    return (
        <div>
            <SearchBar setRecipeData={setRecipeData} />
            <RecipeGrid recipeData={recipeData} />
        </div>
    );
}

export default RecipeSearchAndDisplay;