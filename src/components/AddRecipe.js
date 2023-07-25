// Done by Zlatan

import React, { useState, useContext } from "react";
import { UserContext } from "../App";
import "./Add.scss";

// Helper methods

const mapTypeToEnum = (typeName) => {
  // Helper method to map type name to actual enum value
  const typeMap = {
    Default: "DEFAULT",
    "Egg-free": "EGG_FREE",
    "Gluten-free": "GLUTEN_FREE",
    Diabetic: "DIABETIC",
    "No sugar": "NO_SUGAR",
    "Red meat free": "RED_MEAT_FREE",
    Vegetarian: "VEGETARIAN",
    "Wheat-free": "WHEAT_FREE",
  };

  return typeMap[typeName] || "";
};

const mapDifficultyToEnum = (difficultyName) => {
  // Helper method to map difficulty name to actual enum value
  const difficultyMap = {
    easy: "EASY",
    medium: "MODERATE",
    hard: "HARD",
  };

  return difficultyMap[difficultyName] || "";
};

const App = () => {
  const [showPopup, setShowPopup] = useState(false);
  const [recipeName, setRecipeName] = useState(" ");
  const [type, setType] = useState("");
  const [prepTime, setPrepTime] = useState(0);
  const [serving, setServing] = useState(0);
  const [description, setDescription] = useState("");
  const [difficulty, setDifficulty] = useState("");
  const [amount, setAmount] = useState(0);
  const [amountUnit, setAmountUnit] = useState("mg");
  const { loggedInUser } = useContext(UserContext);
  const [imageLink, setImageLink] = useState(null);
  const [unitOptions, setUnitOptions] = useState([
    "l",
    "ml",
    "kg",
    "mg",
    "g",
    "tablespoon",
    "cup",
    "teaspoon",
    "pound",
    "ounce",
    "inch",
    "centimeter",
  ]);
  const [typeOptions, setTypeOptions] = useState([
    "Default",
    "Egg-free",
    "Gluten-free",
    "Diabetic",
    "No sugar",
    "Red meat free",
    "Vegetarian",
    "Wheat-free",
  ]);

  const [selectedImage, setSelectedImage] = useState(null);

  const handlePopupClose = () => {
    setShowPopup(false);
  };

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    setImageLink(file);
  };

  const handleImageUrlChange = (event) => {
    const url = event.target.value;
    setImageLink(url);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const recipe = {
      recipeName,
      recipeType: mapTypeToEnum(type),
      image_link: imageLink,
      prepTime,
      recipeDescription: description,
      serving,
      difficulty: mapDifficultyToEnum(difficulty),
    };

    console.log("Recipe data:", recipe);

    console.log("User id:", loggedInUser);

    try {
      const response = await fetch("http://localhost:8080/recipes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          userId: loggedInUser,
        },
        body: JSON.stringify(recipe),
      });

      if (response.ok) {
        console.log("Recipe submitted successfully!");
        // show success message
        alert("Recipe submitted successfully!");
      } else {
        console.log("Failed to submit recipe");
        // Additional error handling
      }
    } catch (error) {
      console.log("An error occurred while submitting the recipe:", error);
      // Additional error handling
    }
    //reset
    setRecipeName(" ");
    setType("");
    setPrepTime(0);
    setServing(0);
    setDescription("");
    setDifficulty("");
    setAmount(0);
    setAmountUnit("mg");
    setImageLink(null);
  };

  return (
    <div>
      <h1>Add Recipe</h1>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label>
            Recipe Name:
            <input
              type="text"
              placeholder="Input name"
              value={recipeName}
              onChange={(e) => setRecipeName(e.target.value)}
              className="input-field"
            />
          </label>
        </div>

        <div className="input-group">
          <label>
            Type:
            <select
              value={type}
              placeholder="Preparation time"
              onChange={(e) => setType(e.target.value)}
            >
              <option value="">Select Type</option>
              {typeOptions.map((option) => (
                <option key={option} value={option}>
                  {option}
                </option>
              ))}
            </select>
          </label>
        </div>

        <div className="input-group">
          <label>
            Prep Time:
            <input
              type="text"
              placeholder="Preparation time"
              value={prepTime}
              onChange={(e) => setPrepTime(e.target.value)}
              className="input-field"
            />
          </label>
        </div>

        <div className="input-group">
          <label>
            Serving:
            <input
              type="number"
              placeholder="serving size"
              value={serving}
              onChange={(e) => setServing(e.target.value)}
              className="input-field"
            />
          </label>
        </div>

        <div className="input-group">
          <label>
            Difficulty:
            <select
              value={difficulty}
              onChange={(e) => setDifficulty(e.target.value)}
              className="input-field"
            >
              <option value="">Select Difficulty</option>
              <option value="easy">Easy</option>
              <option value="medium">Medium</option>
              <option value="hard">Hard</option>
            </select>
          </label>
        </div>

        <div className="input-group">
          <label>
            Description:
            <textarea
              type="text"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="input-field"
            />
          </label>
        </div>

        <div className="input-group">
          <label>
            Upload an image:
            <input type="file" accept="image/*" onChange={handleImageUpload} className="input-field"/>
            Or paste a URL:
            <input
              type="text"
              placeholder="Enter image URL"
              onChange={handleImageUrlChange}
              className="input-field"
            />
          </label>
        </div>

        <button type="submit">Save Recipe</button>
      </form>

      {showPopup && (
        <div className="popup">
          <div className="popup-content">
            <span className="close" onClick={handlePopupClose}>
              &times;
            </span>
            <h2>Recipe Saved!</h2>
            <p>Your recipe has been successfully saved.</p>
          </div>
        </div>
      )}
    </div>
  );
};

export default App;
