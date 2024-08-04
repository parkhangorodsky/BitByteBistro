package entity.builder;


import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 * A concrete implementation of the RecipeBuilder interface.
 * This class provides methods to set various attributes of a Recipe
 * and returns the configured Recipe object.
 */
public class DefaultRecipeBuilder implements RecipeBuilder {
    Recipe recipe;

    /**
     * Constructs a DefaultRecipeBuilder with a specified recipe ID.
     * Initializes a new Recipe with the given ID.
     *
     * @param id The unique identifier for the recipe.
     */
    public DefaultRecipeBuilder(String id) {
        this.recipe = new Recipe(id);
    }

    /**
     * Constructs a DefaultRecipeBuilder with a new Recipe object.
     * Initializes a new Recipe with a generated ID.
     */
    public DefaultRecipeBuilder() {
        this.recipe = new Recipe();
    }


    /**
     * Builder methods
     */

    @Override
    public RecipeBuilder buildName(String name) {
        this.recipe.setName(name);
        return this;
    }

    public RecipeBuilder buildImage(BufferedImage image) {
        this.recipe.setImage(image);
        return this;
    }

    public RecipeBuilder buildSmallImage(BufferedImage image) {
        this.recipe.setSmallImage(image);
        return this;
    }

    public RecipeBuilder buildYield(int yield) {
        this.recipe.setYield(yield);
        return this;
    }

    public RecipeBuilder buildInstruction(String instruction) {
        this.recipe.setInstructions(instruction);
        return this;
    }

    @Override
    public RecipeBuilder buildIngredientList(List<Ingredient> ingredients) {
        this.recipe.setIngredientList(ingredients);
        return this;
    }

    public RecipeBuilder buildNutritionMap(Map<String, Nutrition> nutritionMap) {
        this.recipe.setNutritionMap(nutritionMap);
        return this;
    }

    public RecipeBuilder buildDietLabels(List<String> dietLabels) {
        this.recipe.setDietLabels(dietLabels);
        return this;
    }

    public RecipeBuilder buildHealthLabels(List<String> healthLabels) {
        this.recipe.setHealthLabels(healthLabels);
        return this;
    }

    public RecipeBuilder buildCautions(List<String> cautions) {
        this.recipe.setCautions(cautions);
        return this;
    }

    public RecipeBuilder buildTags(List<String> tags) {
        this.recipe.setTags(tags);
        return this;
    }

    public RecipeBuilder buildCuisineType(List<String> cuisineTypes) {
        this.recipe.setCuisineType(cuisineTypes);
        return this;
    }

    public RecipeBuilder buildMealType(List<String> mealTypes) {
        this.recipe.setMealType(mealTypes);
        return this;
    }

    public RecipeBuilder buildDishType(List<String> dishTypes) {
        this.recipe.setDishType(dishTypes);
        return this;
    }

    /**
     * Returns the built Recipe object.
     *
     * @return The Recipe instance.
     */
    @Override
    public Recipe get() {
        return this.recipe;
    }
}
