package entity.builder;

import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public interface RecipeBuilder {
    RecipeBuilder buildName(String name);
    RecipeBuilder buildImage(BufferedImage image);
    RecipeBuilder buildSmallImage(BufferedImage image);
    RecipeBuilder buildYield(int yield);
    RecipeBuilder buildInstruction(String instruction);
    RecipeBuilder buildIngredientList(List<Ingredient> ingredients);
    RecipeBuilder buildNutritionMap(Map<String, Nutrition> nutritionMap);
    RecipeBuilder buildDietLabels(List<String> dietLabels);
    RecipeBuilder buildHealthLabels(List<String> healthLabels);
    RecipeBuilder buildCautions(List<String> cautions);
    RecipeBuilder buildTags(List<String> tags);
    RecipeBuilder buildCuisineType(List<String> cuisineTypes);
    RecipeBuilder buildMealType(List<String> mealTypes);
    RecipeBuilder buildDishType(List<String> dishTypes);
    Recipe get();
}
