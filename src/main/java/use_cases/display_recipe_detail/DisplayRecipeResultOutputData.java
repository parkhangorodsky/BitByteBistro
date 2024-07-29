package use_cases.display_recipe_detail;

import entity.Recipe;

public class DisplayRecipeResultOutputData {
    private Recipe recipe;
    private DisplayRecipeDetailViewModel viewModel;

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
