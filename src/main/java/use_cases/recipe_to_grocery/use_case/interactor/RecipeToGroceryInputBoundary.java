package use_cases.recipe_to_grocery.use_case.interactor;

import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;

/**
 * Interface for the input boundary of the "convert recipes to grocery list" use case.
 * Defines a method to execute the use case with the provided input data.
 */
public interface RecipeToGroceryInputBoundary {

    /**
     * Executes the use case of converting recipes to a grocery list based on the provided input data.
     *
     * @param inputData The input data containing the user information for the conversion process.
     */
    void execute(RecipeToGroceryInputData inputData);
}
