package com.assessment.recipemanager.controller;


import com.assessment.recipemanager.model.Recipe;
import com.assessment.recipemanager.model.ResponseError;
import com.assessment.recipemanager.service.RecipeManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeManagerController.class)
public class RecipeManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeManagerService service;

    private ObjectMapper mapper = new ObjectMapper();

    private final String genericString = "xpto";
    private final String validBody = "{\"ingredients\":\"xpto\",\"instructions\":\"xpto\",\"is_vegetarian\":true,\"suitable_for\":2}";
    private final String invalidBody = "{\"instructions\":\"xpto\",\"is_vegetarian\":true,\"suitable_for\":2}";

    @BeforeEach
    private void setUp(){
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void givenRecipes_whenGetRecipes_thenStatus200() throws Exception {


        Stream<Recipe> recipes = Stream.of(new Recipe(true, 2, genericString, genericString));
        Mockito.when(service.retrieveMany(ArgumentMatchers.any()))
                .thenReturn((recipes.toList()));

        ResultActions resultActions = mockMvc.perform(get("/api/v1/recipes")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Recipe[] parsed = mapper.readValue(contentAsString, Recipe[].class);

        assertThat(parsed.length).isEqualTo(1);

    }

    @Test
    public void givenRecipes_whenCreatingNewRecipeWithValidBody_thenStatus201() throws Exception {
        mockMvc.perform(post("/api/v1/recipes")
                .content(validBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenRecipes_whenCreatingNewRecipeWithInvalidBody_thenStatus400() throws Exception {
        mockMvc.perform(post("/api/v1/recipes")
                .content(invalidBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenRecipes_whenDeletingRecipe_thenStatus200() throws Exception {
        mockMvc.perform(delete("/api/v1/recipes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test()
    public void givenRecipes_whenDeletingANonExistentRecipe_thenStatus404() throws Exception {
        doThrow(EntityNotFoundException.class).when(service).deleteById(ArgumentMatchers.any());

        MvcResult result = mockMvc.perform(delete("/api/v1/recipes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        ResponseError error = mapper.readValue(result.getResponse().getContentAsString(), ResponseError.class);
        assertThat(error.message()).isEqualTo("Could not find the entity with the given Id");
    }

    @Test
    public void givenRecipes_whenUpdatingRecipe_thenStatus200() throws Exception {
        mockMvc.perform(put("/api/v1/recipes/1")
                .content(validBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test()
    public void givenRecipes_whenUpdatingANonExistentRecipe_thenStatus404() throws Exception {
        doThrow(EntityNotFoundException.class).when(service).update(ArgumentMatchers.any(), ArgumentMatchers.any(Recipe.class));

        MvcResult result = mockMvc.perform(put("/api/v1/recipes/1")
                .content(validBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        ResponseError error = mapper.readValue(result.getResponse().getContentAsString(), ResponseError.class);
        assertThat(error.message()).isEqualTo("Could not find the entity with the given Id");
    }



    @Test
    public void givenRecipes_whenGetRecipesWithId_thenStatus200() throws Exception {

        Recipe recipe = new Recipe(true, 2, genericString, genericString);
        Mockito.when(service.retrieve(ArgumentMatchers.any()))
                .thenReturn(recipe);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/recipes/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Recipe parsed = mapper.readValue(contentAsString, Recipe.class);

        assertThat(parsed.getIngredients()).isEqualTo(genericString);
        assertThat(parsed.getInstructions()).isEqualTo(genericString);
        assertThat(parsed.isVegetarian()).isTrue();
        assertThat(parsed.getSuitableFor()).isEqualTo(2);
        assertThat(parsed.getCreatedAt()).isNotNull();
    }


}
