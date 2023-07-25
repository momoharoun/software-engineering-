package com.example.recipico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipico.model.Ingredient;
import com.example.recipico.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService{


    @Autowired
    private IngredientRepository ingredientRepository;


    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(Long ingredientId) {
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(ingredientId);
        if(ingredientOpt.isPresent())
            return ingredientOpt.get();
        else
            throw new RuntimeException("ingredient not found.");
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        Ingredient ingredientDetail = ingredientRepository.save(ingredient);
        System.out.println("ingredient saved to db with ingredientId : " + ingredientDetail.getIngredientId() + "and ingredient name : " + ingredientDetail.getIngredientName());
    }

    @Override
    public void deleteIngredientyId(Long ingredientId) {
        Optional<Ingredient> IngredientOpt = ingredientRepository.findById(ingredientId);
        if(IngredientOpt.isPresent())
            ingredientRepository.deleteById(ingredientId);
        else
            throw new RuntimeException("Ingredient not found.");
    }
    
}
