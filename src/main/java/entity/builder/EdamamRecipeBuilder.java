package entity.builder;


import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;

import java.util.List;
import java.util.Map;

public class EdamamRecipeBuilder implements RecipeBuilder {
    Recipe recipe;

    public EdamamRecipeBuilder(String id) {
        this.recipe = new Recipe(id);
    }

    public EdamamRecipeBuilder() {
        this.recipe = new Recipe();
    }

    @Override
    public RecipeBuilder buildName(String name) {
        this.recipe.setName(name);
        return this;
    }

    public RecipeBuilder buildImage(String image) {
        this.recipe.setImage(image);
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

    public RecipeBuilder buildTotalDailyMap(Map<String, Nutrition> totalDailyMap) {
        this.recipe.setTotalDailyMap(totalDailyMap);
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

    @Override
    public Recipe get() {
        return this.recipe;
    }
}
