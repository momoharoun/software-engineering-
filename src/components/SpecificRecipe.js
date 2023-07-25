import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { UserContext } from "../App";
import "./SpecificRecipe.css";

function SpecificRecipe() {
  const { recipeId } = useParams();
  const { loggedInUser } = React.useContext(UserContext);
  const [recipe, setRecipe] = React.useState({});

  useEffect(() => {
    const getRecipe = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/recipes/${recipeId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              userId: loggedInUser,
            },
          }
        );
        const data = await response.json();
        setRecipe(data);
      } catch (error) {
        console.error(error);
      }
    };
    getRecipe();
  }, [recipeId, loggedInUser]);

  if (!recipe) {
    return <div>Loading...</div>;
  }

  return (
    <div className="recipe-page">
      <h1 className="recipe-title">{recipe.recipeName}</h1>
      <div className="recipe-details">
        <p>
          <strong>Preparation time:</strong> {recipe.prepTime}
        </p>
        <p>
          <strong>Servings:</strong> {recipe.servings}
        </p>
        <p>
          <strong>Difficulty:</strong> {recipe.difficulty}
        </p>
      </div>
      <h2 className="description-title">Description</h2>
      <div className="recipe-description-container">
        <p className="recipe-description">{recipe.recipeDescription}</p>
      </div>
    </div>
  );
}

export default SpecificRecipe;
