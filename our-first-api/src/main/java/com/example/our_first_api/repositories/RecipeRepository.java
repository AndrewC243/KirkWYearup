package com.example.our_first_api.repositories;

import com.example.our_first_api.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findRecipeByNameContainsIgnoreCase(String name);
    List<Recipe> findRecipeByInstructionsContainsIgnoreCase(String instructions);
}
