// Done by Momo

import React, { useState, useContext } from 'react';
import { UserContext } from '../App';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch, faBars } from '@fortawesome/free-solid-svg-icons';

// Map labels for preparation time to their corresponding numeric values
const preparationTimeMap = {
  "LESS_THAN_30_MIN": 30,
  "LESS_THAN_1_HOUR": 60,
  "LESS_THAN_2_HOURS": 120,
  "MORE_THAN_2_HOURS": 9999
};


function SearchBar({ setRecipeData }) {

  const [query, setQuery] = useState("");
  const [recipes, setRecipes] = useState([]);
  const [alert, setAlert] = useState("");
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedType, setSelectedType] = useState('');
  const [selectedDifficulty, setSelectedDifficulty] = useState('');
  const [selectedPreparationTime, setSelectedPreparationTime] = useState('MORE_THAN_2_HOURS');
  const { loggedInUser } = useContext(UserContext) // TODO: Remove this line once authentication is implemented
  const [categoriesOpen, setCategoriesOpen] = useState(false); // State to track the categories' open/close state

  const handleInputChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleTypeChange = (event) => {
    setSelectedType(event.target.value);
  };

  const handleDifficultyChange = (event) => {
    setSelectedDifficulty(event.target.value);
  };

  const handlePreparationTimeChange = (event) => {
    setSelectedPreparationTime(event.target.value);
  };

  const handleSearch = async (event) => {
    event.preventDefault();
    const queryParams = new URLSearchParams();
    queryParams.append("searchQuery", searchQuery);
    queryParams.append("type", selectedType);
    queryParams.append("difficulty", selectedDifficulty);
    queryParams.append("prepTime", preparationTimeMap[selectedPreparationTime]);

    // Print user id to console
    console.log("User id:", loggedInUser);
    
    try {
      const response = await fetch(`http://localhost:8080/recipes?${queryParams.toString()}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "userId": loggedInUser,
      }});
      if (!response.ok) {
        throw new Error("Failed to fetch recipes");
      }
      const data = await response.json();
      console.log(data);

      // if image link is null, set it to a default image
      data.forEach((recipe) => {
        if (recipe.image_link === null) {
          recipe.image_link = "./default.jpg";
        }
      });

      // set the recipe data
      setRecipeData(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleCategoriesToggle = () => {
    setCategoriesOpen(!categoriesOpen);
  };

  return (
  
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', paddingTop: '20px' }}>
      <form onSubmit={handleSearch} style={{ display: 'flex', alignItems: 'center', marginBottom: '20px' }}>
        <button type="button" onClick={handleCategoriesToggle} style={{ marginRight: '10px', borderRadius: '50%', width: '50px', height: '50px' }}>
          <FontAwesomeIcon icon={faBars} size="lg" />
        </button>
        <input
          type="text"
          placeholder="SEARCH FOR A RECIPE OF YOUR CHOICE"
          value={searchQuery}
          onChange={handleInputChange}
          style={{ width: '400px', height: '40px', fontSize: '15px', borderRadius: '50px' }}
        />
        <button type="submit" style={{ marginLeft: '10px', borderRadius: '50%', width: '50px', height: '50px' }}>
          <FontAwesomeIcon icon={faSearch} size="lg" />
        </button>
      </form>

      {categoriesOpen && (
        <table style={{ marginTop: '20px' }}>
          <tbody>
            <tr>
              <td>Type:</td>
              <td>
                <select value={selectedType} onChange={handleTypeChange}>
                  <option value="">All</option>
                  <option value="DEFAULT">Default</option>
                  <option value="EGG_FREE">Egg-Free</option>
                  <option value="GLUTEN_FREE">Gluten-Free</option>
                  <option value="DIABETIC">Diabetic</option>
                  <option value="NO_SUGAR">No Sugar</option>
                  <option value="RED_MEAT_FREE">Red Meat-Free</option>
                  <option value="VEGETARIAN">Vegetarian</option>
                  <option value="WHEAT_FREE">Wheat-Free</option>
                </select>
              </td> 
            </tr>
            <tr>
              <td>Difficulty:</td>
              <td>
                <select value={selectedDifficulty} onChange={handleDifficultyChange}>
                  <option value="">All</option>
                  <option value="EASY">Easy</option>
                  <option value="MODERATE">Moderate</option>
                  <option value="HARD">Hard</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>Preparation Time:</td>
              <td>
                <select value={selectedPreparationTime} onChange={handlePreparationTimeChange}>
                  <option value="">All</option>
                  <option value="LESS_THAN_30_MIN">Less than 30 min</option>
                  <option value="LESS_THAN_1_HOUR">Less than 1 hour</option>
                  <option value="LESS_THAN_2_HOURS">Less than 2 hours</option>
                  <option value="MORE_THAN_2_HOURS">All</option>
                </select>
              </td>
            </tr>
          </tbody>
        </table>
      )}

        <div style={{ marginTop: '50px', textAlign: 'center', fontFamily: 'Bebas Neue, sans-serif', fontSize: '15px' }}>
        <p>BELOW ARE THE MOST POPULAR RECIPES:</p>
 
      </div>
    </div>
  );
};

export default SearchBar;
