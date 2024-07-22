package use_cases.recipe_to_grocery.interface_adapter.presenter;

import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

/**
 * Interface for presenting the output of converting recipes to a grocery list.
 * Implementing classes define methods to prepare and display a success view.
 */
public interface RecipeToGroceryOutputBoundary {

    /**
     * Prepares the view to display successfully converted recipes as a grocery list.
     *
     * @param recipes The output data containing converted recipes.
     */
    void prepareSuccessView(RecipeToGroceryOutputData recipes);
}