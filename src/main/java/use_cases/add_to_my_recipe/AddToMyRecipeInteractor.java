package use_cases.add_to_my_recipe;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;

import java.util.List;

public class AddToMyRecipeInteractor implements AddToMyRecipeInputBoundary {
    AddToMyRecipePresenter presenter;
    UserDataAccessInterface userDAO;

    public AddToMyRecipeInteractor(AddToMyRecipePresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }

    @Override
    public void execute(AddToMyRecipeInputData inputData) {
        Recipe newRecipe = inputData.getRecipe();
        User user = LoggedUserData.getLoggedInUser();

        for (Recipe recipe : user.getRecipes()) {
            if (recipe.getId().equals(newRecipe.getId())) {
                presenter.prepareFailureView("recipe already exists", inputData.getParentModel());
                return;
            }
        }

        // write in DAO
        userDAO.addRecipe(user, newRecipe);
        user.addRecipe(newRecipe);

        AddToMyRecipeOutputData outputData = new AddToMyRecipeOutputData(inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }
}
