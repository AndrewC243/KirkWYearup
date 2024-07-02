package com.example.our_first_api.services;

import com.example.our_first_api.exceptions.ResourceNotFoundException;
import com.example.our_first_api.models.Recipe;
import com.example.our_first_api.models.dto.CreateRecipeDTO;
import com.example.our_first_api.models.dto.RecipeDTO;
import com.example.our_first_api.models.dto.UpdateRecipeDTO;
import com.example.our_first_api.models.mapper.RecipeMapper;
import com.example.our_first_api.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll().stream().map(RecipeMapper::toDTO).toList();
    }

    public RecipeDTO getRecipeById(long id) {
        return recipeRepository.findById(id)
                .map(RecipeMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id " + id));
    }

    public void deleteRecipeById(long id) {
        recipeRepository.findById(id).ifPresent(recipeRepository::delete);
    }

    public RecipeDTO createRecipe(CreateRecipeDTO recipeDTO) {
        Recipe recipe = RecipeMapper.toEntity(recipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeMapper.toDTO(savedRecipe);
    }

    public RecipeDTO updateRecipe(long id, UpdateRecipeDTO recipeDTO) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id " + id));
        recipe.setName(recipeDTO.getName());
        recipe.setInstructions(recipeDTO.getInstructions());
        recipe.setMinutesToCook(recipeDTO.getMinutesToCook());
        recipeRepository.save(recipe);

        return RecipeMapper.toDTO(recipe);
    }

    public List<RecipeDTO> searchRecipes(String name) {
        return recipeRepository
                .findRecipeByNameContainsIgnoreCase(name)
                .stream().map(RecipeMapper::toDTO)
                .toList();
    }
}
