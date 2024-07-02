package com.example.our_first_api.controllers;

import com.example.our_first_api.models.dto.CreateRecipeDTO;
import com.example.our_first_api.models.dto.RecipeDTO;
import com.example.our_first_api.models.dto.UpdateRecipeDTO;
import com.example.our_first_api.repositories.RecipeRepository;
import com.example.our_first_api.services.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getRecipes() {
        var recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable long id) {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody CreateRecipeDTO recipe) {
        var madeRecipe = recipeService.createRecipe(recipe);

        return ResponseEntity.ok(madeRecipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(@Valid @RequestBody UpdateRecipeDTO recipe, @PathVariable long id) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipe));
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeDTO>> searchRecipes(@RequestParam String name) {
        return ResponseEntity.ok(recipeService.searchRecipes(name));
    }
}
