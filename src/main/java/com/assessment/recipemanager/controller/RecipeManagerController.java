package com.assessment.recipemanager.controller;

import com.assessment.recipemanager.model.Recipe;
import com.assessment.recipemanager.service.RecipeManagerService;
import com.assessment.recipemanager.service.RecipeManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeManagerController {

    @Autowired
    private RecipeManagerService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@Valid @RequestBody Recipe recipe){
        service.create(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Recipe> get(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Recipe>(service.retrieve(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Recipe>> getQuery(@RequestParam(name = "isVegetarian") Optional<Boolean> isVegetarian){
        return new ResponseEntity<List<Recipe>>(service.retrieveMany(isVegetarian), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @Valid @RequestBody Recipe recipe){
        service.update(id, recipe);
    }


    @DeleteMapping("{id}" )
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        service.deleteById(id);
    }
}
