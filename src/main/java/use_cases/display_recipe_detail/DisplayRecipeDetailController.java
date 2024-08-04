package use_cases.display_recipe_detail;

import entity.Recipe;

import java.util.ArrayList;

public class DisplayRecipeDetailController {
    final DisplayRecipeDetailInputBoundary interactor;

    public DisplayRecipeDetailController(DisplayRecipeDetailInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(Recipe recipe, DisplayRecipeDetailViewModel viewModel) {
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(recipe, viewModel);
        interactor.execute(inputData);
    }
}
