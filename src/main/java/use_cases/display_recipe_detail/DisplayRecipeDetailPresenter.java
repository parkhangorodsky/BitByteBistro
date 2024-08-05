package use_cases.display_recipe_detail;

import entity.Recipe;

/**
 * Prepares the view model for displaying recipe details after the business logic is executed.
 */
public class DisplayRecipeDetailPresenter implements DisplayRecipeDetailOutputBoundary {

    /**
     * Constructs a DisplayRecipeDetailPresenter.
     */
    public DisplayRecipeDetailPresenter() {
    }

    /**
     * Updates the view model with the recipe details and triggers a property change event.
     *
     * @param outputData The output data containing the recipe and view model.
     */
    @Override
    public void prepareSuccessView(DisplayRecipeResultOutputData outputData) {
        DisplayRecipeDetailViewModel viewModel = outputData.getViewModel();
        Recipe recipe = outputData.getRecipe();
        viewModel.setRecipe(recipe);
        viewModel.firePropertyChange("initialized");
    }
}