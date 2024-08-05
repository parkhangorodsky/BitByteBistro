package use_cases.display_recipe_detail;

import entity.Recipe;
import java.util.ArrayList;

/**
 * The DisplayRecipeDetailController class handles user requests to display the details
 * of a specific recipe. It acts as an intermediary between the user interface and the
 * business logic, passing the necessary data to the interactor for processing.
 */
public class DisplayRecipeDetailController {
    final DisplayRecipeDetailInputBoundary interactor;

    /**
     * Constructs a DisplayRecipeDetailController with the specified interactor.
     *
     * @param interactor The interactor responsible for executing the business logic of displaying recipe details.
     */
    public DisplayRecipeDetailController(DisplayRecipeDetailInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the use case for displaying recipe details.
     * <p>
     * This method creates an input data object containing the recipe and view model,
     * and then passes it to the interactor for processing.
     * </p>
     *
     * @param recipe   The recipe whose details are to be displayed.
     * @param viewModel The view model to be updated with the recipe details.
     */
    public void execute(Recipe recipe, DisplayRecipeDetailViewModel viewModel) {
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(recipe, viewModel);
        interactor.execute(inputData);
    }
}

