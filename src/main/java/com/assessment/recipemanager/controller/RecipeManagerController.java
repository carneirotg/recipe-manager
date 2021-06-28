package com.assessment.recipemanager.controller;

import com.assessment.recipemanager.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class RecipeManagerController {

    @PostMapping("recipes")
    public ResponseEntity<String> create(@RequestBody Recipe recipe){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("recipes/{id}")
    public ResponseEntity<Recipe> get(@RequestParam int id){
        Recipe recipe = new Recipe();
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }
}
