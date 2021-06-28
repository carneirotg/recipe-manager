package com.assessment.recipemanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity

public class Recipe{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDateTime createdAt;

    private RecipeType type;
    private int suitableFor;
    private String ingredients;
    private String instructions;

    public Recipe(){

    }

    public Recipe(RecipeType type, int suitableFor, String ingredients, String instructions){
        this.createdAt = LocalDateTime.now();
        this.type = type;
        this.suitableFor = suitableFor;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public int getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(int suitableFor) {
        this.suitableFor = suitableFor;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

}
