package use_cases.display_recipe_detail;

import entity.Recipe;

import java.util.ArrayList;

/**
 * Controller class responsible for handling the display of detailed information about a recipe.
 * This class serves as a bridge between the user interface and the business logic for displaying recipe details.
 */
public class DisplayRecipeDetailController {
    final DisplayRecipeDetailInputBoundary interactor;

    /**
     * Constructs a new {@code DisplayRecipeDetailController} with the specified interactor.
     *
     * @param interactor The interactor that handles the execution of the display recipe detail use case.
     *                   This interactor should implement the {@code DisplayRecipeDetailInputBoundary} interface.
     */
    public DisplayRecipeDetailController(DisplayRecipeDetailInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the use case of displaying detailed information about a recipe.
     *
     * This method passes the recipe data and view model to the interactor, which handles the business logic
     * for displaying the recipe details.
     *
     * @param recipe    The {@code Recipe} object containing the recipe details to be displayed.
     * @param viewModel The {@code DisplayRecipeDetailViewModel} used to format and present the recipe details in the view.
     */
    public void execute(Recipe recipe, DisplayRecipeDetailViewModel viewModel) {
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(recipe, viewModel);
        interactor.execute(inputData);
    }
}
