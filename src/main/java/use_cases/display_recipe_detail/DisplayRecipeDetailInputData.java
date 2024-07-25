package use_cases.display_recipe_detail;

import entity.Recipe;
import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;

public class DisplayRecipeDetailInputData {
    private Recipe recipe;
    private DisplayRecipeDetailViewModel viewModel;

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
