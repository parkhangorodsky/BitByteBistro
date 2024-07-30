package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;

public class AddToMyRecipeInteractor implements AddToMyRecipeInputBoundary {
    AddToMyRecipePresenter presenter;

    public AddToMyRecipeInteractor(AddToMyRecipePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(AddToMyRecipeInputData inputData) {
        User user = inputData.getLoggedInUser();
        Recipe newRecipe = inputData.getRecipe();

        for (Recipe recipe : user.getRecipes()) {
            if (recipe.getId().equals(newRecipe.getId())) {
                presenter.prepareFailureView("recipe already exists", inputData.getParentModel());
                return;
            }
        }

        inputData.getLoggedInUser().addRecipe(inputData.getRecipe());
        // write in DAO

        AddToMyRecipeOutputData outputData = new AddToMyRecipeOutputData(inputData.getLoggedInUser());
        presenter.prepareSuccessView(outputData);
    }
}
