package use_cases.recipe_to_grocery.use_case.input_data;

import entity.Recipe;

import java.util.List;

/**
 * Interface representing the boundary for converting recipes to a shopping list of ingredients.
 */

public interface RecipeToGroceryInputBoundary {

    /**
     * Fetches a list of recipes from the data source.
     * @return List of Recipe objects fetched from the data source.
     */
    List<Recipe> fetchRecipes();

    /**
     * Converts a list of recipes to a shopping list of ingredients.
     * @param recipeToGroceryInputData Input data containing recipes to convert.
     */
    void execute(RecipeToGroceryInputData recipeToGroceryInputData);
}

