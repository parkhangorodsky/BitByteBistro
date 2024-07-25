package use_cases.display_recipe_detail;

import entity.Recipe;

public class DisplayRecipeDetailPresenter implements DisplayRecipeDetailOutputBoundary{

    public DisplayRecipeDetailPresenter() {
    }


    @Override
    public void prepareSuccessView(DisplayRecipeResultOutputData outputData) {
        DisplayRecipeDetailViewModel viewModel = outputData.getViewModel();
        Recipe recipe = outputData.getRecipe();
        viewModel.setRecipe(recipe);
        viewModel.firePropertyChange();
    }
}
