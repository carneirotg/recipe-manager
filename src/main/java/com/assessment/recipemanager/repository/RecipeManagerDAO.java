package com.assessment.recipemanager.repository;

import com.assessment.recipemanager.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeManagerDAO extends JpaRepository<Recipe, Long> {
}
