package use_cases.display_recipe_detail;

/**
 * The DisplayRecipeDetailInteractor class handles the business logic for the
 * display recipe detail use case.
 * <p>
 * It implements the DisplayRecipeDetailInputBoundary interface and processes
 * the input data to generate the output data that will be passed to the presenter.
 * </p>
 */
public class DisplayRecipeDetailInteractor implements DisplayRecipeDetailInputBoundary {
    private final DisplayRecipeDetailOutputBoundary presenter;

    /**
     * Constructs a DisplayRecipeDetailInteractor with the specified presenter.
     *
     * @param presenter The output boundary that will handle the presentation of the recipe details.
     */
    public DisplayRecipeDetailInteractor(DisplayRecipeDetailOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Executes the use case to display recipe details.
     * <p>
     * This method processes the input data and prepares the output data, which is then passed
     * to the presenter to prepare the view.
     * </p>
     *
     * @param inputData The input data containing the recipe and view model to be processed.
     */
    @Override
    public void execute(DisplayRecipeDetailInputData inputData) {
        DisplayRecipeResultOutputData outputData = new DisplayRecipeResultOutputData(inputData.getRecipe(),
                inputData.getViewModel());
        presenter.prepareSuccessView(outputData);
    }
}