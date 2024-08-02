package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;

public class AddToMyRecipeInteractor implements AddToMyRecipeInputBoundary {
    AddToMyRecipePresenter presenter;
    UserDataAccessInterface userDAO;

    public AddToMyRecipeInteractor(AddToMyRecipePresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
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
        userDAO.addRecipe(user, newRecipe);
        // write in DAO

        AddToMyRecipeOutputData outputData = new AddToMyRecipeOutputData(inputData.getLoggedInUser(), inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }
}
