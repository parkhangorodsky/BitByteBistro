package use_cases.display_recipe_detail;

import entity.Recipe;

/**
 * Encapsulates the output data for displaying recipe details.
 */
public class DisplayRecipeResultOutputData {
    private final Recipe recipe;
    private final DisplayRecipeDetailViewModel viewModel;

    /**
     * Constructs a DisplayRecipeResultOutputData instance with the specified recipe and view model.
     *
     * @param recipe   The recipe to be displayed.
     * @param viewModel The view model to be updated with recipe details.
     */
    public DisplayRecipeResultOutputData(Recipe recipe, DisplayRecipeDetailViewModel viewModel) {
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