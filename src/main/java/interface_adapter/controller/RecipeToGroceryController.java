package interface_adapter.controller;

import use_case.input_data.RecipeToGroceryInputBoundary;
import use_case.input_data.RecipeToGroceryInputData;

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
