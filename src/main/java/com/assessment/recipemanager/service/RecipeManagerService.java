package com.assessment.recipemanager.service;

import com.assessment.recipemanager.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeManagerService {

    void create(Recipe recipe);

    Recipe retrieve(Long id);

    List<Recipe> retrieveMany(Optional<Boolean> isVegetarian);

    void deleteById(Long id);

    void update(Long id, Recipe recipe);
}

