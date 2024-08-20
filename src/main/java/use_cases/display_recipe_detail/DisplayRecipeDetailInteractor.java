package use_cases.display_recipe_detail;

/**
 * Interactor class responsible for processing the display of detailed recipe information.
 * This class implements the use case of preparing and presenting detailed recipe data.
 */
public class DisplayRecipeDetailInteractor implements DisplayRecipeDetailInputBoundary{
    private DisplayRecipeDetailOutputBoundary presenter;

    /**
     * Constructs a new {@code DisplayRecipeDetailInteractor} with the specified presenter.
     *
     * @param presenter The presenter that will handle the output data and prepare the view for success.
     *                  This presenter should implement the {@code DisplayRecipeDetailOutputBoundary} interface.
     */
    public DisplayRecipeDetailInteractor(DisplayRecipeDetailOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Executes the use case of displaying detailed information about a recipe.
     *
     * This method processes the input data, prepares the output data, and invokes the presenter
     * to handle the success view for displaying recipe details.
     *
     * @param inputData The {@code DisplayRecipeDetailInputData} containing the recipe and view model for display.
     */
    @Override
    public void execute(DisplayRecipeDetailInputData inputData) {
        DisplayRecipeResultOutputData outputData = new DisplayRecipeResultOutputData(inputData.getRecipe(),
                inputData.getViewModel());
        presenter.prepareSuccessView(outputData);
    }
}
