package com.assessment.recipemanager.repository;

import com.assessment.recipemanager.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findAllByIsVegetarian(boolean isVegetarian);
}
