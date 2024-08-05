package use_cases.display_recipe_detail;

import entity.Recipe;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;

/**
 * Encapsulates the input data for displaying recipe details.
 */
public class DisplayRecipeDetailInputData {
    private final Recipe recipe;
    private final DisplayRecipeDetailViewModel viewModel;

    /**
     * Constructs an instance with the specified recipe and view model.
     *
     * @param recipe   The recipe to display.
     * @param viewModel The view model to update with recipe details.
     */
    public DisplayRecipeDetailInputData(Recipe recipe, DisplayRecipeDetailViewModel viewModel) {
        this.recipe = recipe;
        this.viewModel = viewModel;
    }

    /**
     * Returns the recipe to be displayed.
     *
     * @return The recipe.
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Returns the view model to be updated.
     *
     * @return The view model.
     */
    public DisplayRecipeDetailViewModel getViewModel() {
        return viewModel;
    }
}