package use_cases.recipe_to_grocery.interface_adapter.controller;

import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputBoundary;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.use_case.interactor.RecipeToGroceryInteractor;

public class RecipeToGroceryController {
    final RecipeToGroceryInputBoundary recipeToGroceryInteractor;

    public RecipeToGroceryController(RecipeToGroceryInputBoundary recipeToGroceryInteractor) {
        this.recipeToGroceryInteractor = recipeToGroceryInteractor;
    }

    public void execute(String queryString) {
        RecipeToGroceryInputData recipeToGroceryInputData = new RecipeToGroceryInputData(queryString);
        recipeToGroceryInteractor.execute(recipeToGroceryInputData);
    }
}
