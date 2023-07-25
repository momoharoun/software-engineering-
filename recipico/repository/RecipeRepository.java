package com.example.recipico.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipico.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>  {

}
