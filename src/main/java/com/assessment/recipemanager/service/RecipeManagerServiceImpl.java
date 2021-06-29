package com.assessment.recipemanager.service;

import com.assessment.recipemanager.model.Recipe;
import com.assessment.recipemanager.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class RecipeManagerServiceImpl implements RecipeManagerService{

    @Autowired
    private RecipeRepository recipeRepository;

    public void create(Recipe recipe) {
        try {
            recipeRepository.save(recipe);
        } catch (Exception e){

        }
    }

    public Recipe retrieve(Long id) {
        try{
            return recipeRepository.findById(id).get();
        } catch (Exception e){
            throw new EntityNotFoundException();
        }
    }

    public List<Recipe> retrieveMany(Optional<Boolean> isVegetarian){

        try{
            if (isVegetarian.isEmpty()){
                return (List<Recipe>) recipeRepository.findAll();
            }
            return recipeRepository.findAllByIsVegetarian(isVegetarian.get());

        } catch (Exception e){
            throw new EntityNotFoundException();
        }
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    public void update(Long id, Recipe recipe) {
        Optional<Recipe> persistedRecipe = recipeRepository.findById(id);
        if (!persistedRecipe.isEmpty()){
            Recipe updatedRecipe = updateRecipeObject(persistedRecipe.get(), recipe);
            recipeRepository.save(updatedRecipe);
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Recipe updateRecipeObject(Recipe oldRecipe, Recipe newRecipe){
       oldRecipe.setVegetarian(newRecipe.isVegetarian());
       oldRecipe.setSuitableFor(newRecipe.getSuitableFor());
       oldRecipe.setIngredients(newRecipe.getIngredients());
       oldRecipe.setInstructions(newRecipe.getInstructions());
       return oldRecipe;
    }
}
