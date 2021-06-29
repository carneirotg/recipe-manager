package com.assessment.recipemanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Recipe", schema = "dbo")
@Getter
@Setter
public class Recipe{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();;

    @Column(name = "is_vegetarian")
    @JsonProperty("is_vegetarian")
    @NotNull(message = "is_vegetarian is a mandatory field")
    private boolean isVegetarian;

    @JsonProperty("suitable_for")
    @Column(name = "suitable_for")
    @NotNull(message = "suitable_for is a mandatory field")
    private int suitableFor;

    @Lob
    @Column(columnDefinition = "text")
    @NotNull(message = "ingredients is a mandatory field")
    private String ingredients;

    @Lob
    @Column(columnDefinition = "text")
    @NotNull(message = "instructions is a mandatory field")
    private String instructions;

    public Recipe(){

    }

    public Recipe(boolean isVegetarian, int suitableFor, String ingredients, String instructions){
        this.createdAt = LocalDateTime.now();
        this.isVegetarian = isVegetarian;
        this.suitableFor = suitableFor;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}
