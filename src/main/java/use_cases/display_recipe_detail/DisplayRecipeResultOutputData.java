package use_cases.display_recipe_detail;

import entity.Recipe;

/**
 * Data class representing the output data for displaying detailed recipe information.
 * This class holds the recipe and view model necessary for presenting the recipe details.
 */
public class DisplayRecipeResultOutputData {
    private Recipe recipe;
    private DisplayRecipeDetailViewModel viewModel;

    /**
     * Constructs a new {@code DisplayRecipeResultOutputData} with the specified recipe and view model.
     *
     * @param recipe    The {@code Recipe} object containing the details of the recipe to be displayed.
     * @param viewModel The {@code DisplayRecipeDetailViewModel} used to format and present the recipe details.
     */
    public DisplayRecipeResultOutputData(Recipe recipe, DisplayRecipeDetailViewModel viewModel) {
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
