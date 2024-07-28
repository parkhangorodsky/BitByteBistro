package use_cases.add_to_my_recipe;

public class AddToMyRecipeInteractor implements AddToMyRecipeInputBoundary {
    AddToMyRecipePresenter presenter;

    public AddToMyRecipeInteractor(AddToMyRecipePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(AddToMyRecipeInputData inputData) {
        inputData.getLoggedInUser().addRecipe(inputData.getRecipe());
        // write in DAO

        AddToMyRecipeOutputData outputData = new AddToMyRecipeOutputData(inputData.getLoggedInUser());
        presenter.prepareSuccessView(outputData);
    }
}
