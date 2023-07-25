import React from 'react';
import './RecipeGrid.css'; 

function RecipeGrid({ recipeData }) {

  const openSpecificRecipe = (recipe) => {
    window.open(`/recipe/${recipe.recipeId}`, "_blank");
  };

    return (
      <div className="recipe-grid">
        {recipeData.map((recipe) => (
          <div key={recipe.recipeId} className="recipe-box" onClick={() => openSpecificRecipe(recipe)}>
            <h3 className="recipe-name">{recipe.recipeName}</h3>
            <img src={recipe.image_link} alt={recipe.recipe_name} className="recipe-image" />
          </div>
        ))}
      </div>
    );
  }

export default RecipeGrid;