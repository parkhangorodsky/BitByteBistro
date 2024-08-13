package use_cases.display_recipe_detail;

import entity.Recipe;

/**
 * Presenter class responsible for preparing and displaying detailed recipe information.
 * This class implements the output boundary of the display recipe detail use case,
 * formatting and presenting the recipe details to the view.
 */
public class DisplayRecipeDetailPresenter implements DisplayRecipeDetailOutputBoundary{

    /**
     * Constructs a new {@code DisplayRecipeDetailPresenter}.
     * This default constructor is provided to instantiate the presenter.
     */
    public DisplayRecipeDetailPresenter() {
    }

    /**
     * Prepares the success view with the detailed recipe information.
     *
     * This method takes the output data containing the recipe and view model, updates the view model with
     * the recipe details, and triggers a property change event to notify the view of the initialization.
     *
     * @param outputData The {@code DisplayRecipeResultOutputData} containing the recipe and view model for display.
     */
    @Override
    public void prepareSuccessView(DisplayRecipeResultOutputData outputData) {
        DisplayRecipeDetailViewModel viewModel = outputData.getViewModel();
        Recipe recipe = outputData.getRecipe();
        viewModel.setRecipe(recipe);
        viewModel.firePropertyChange("initialized");
    }
}
