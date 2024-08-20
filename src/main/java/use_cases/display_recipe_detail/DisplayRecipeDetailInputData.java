package use_cases.display_recipe_detail;

import entity.Recipe;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;

/**
 * Data class representing the input data required for displaying detailed recipe information.
 * This class holds the recipe and view model needed to process the display recipe detail use case.
 */
public class DisplayRecipeDetailInputData {
    private Recipe recipe;
    private DisplayRecipeDetailViewModel viewModel;

    /**
     * Constructs a new {@code DisplayRecipeDetailInputData} object with the specified recipe and view model.
     *
     * @param recipe    The {@code Recipe} object containing the details of the recipe to be displayed.
     * @param viewModel The {@code DisplayRecipeDetailViewModel} used to format and present the recipe details.
     */
    public DisplayRecipeDetailInputData(Recipe recipe, DisplayRecipeDetailViewModel viewModel) {
        this.recipe = recipe;
        this.viewModel = viewModel;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public DisplayRecipeDetailViewModel getViewModel() {
        return viewModel;
    }
}
