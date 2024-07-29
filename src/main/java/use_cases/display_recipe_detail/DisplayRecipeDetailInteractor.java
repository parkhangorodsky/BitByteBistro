package use_cases.display_recipe_detail;

public class DisplayRecipeDetailInteractor implements DisplayRecipeDetailInputBoundary{
    private DisplayRecipeDetailOutputBoundary presenter;
    public DisplayRecipeDetailInteractor(DisplayRecipeDetailOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(DisplayRecipeDetailInputData inputData) {
        DisplayRecipeResultOutputData outputData = new DisplayRecipeResultOutputData(inputData.getRecipe(),
                inputData.getViewModel());
        presenter.prepareSuccessView(outputData);
    }
}
