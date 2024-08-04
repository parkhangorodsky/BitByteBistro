package use_cases.recently_viewed_recipes;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import use_cases.add_to_my_recipe.AddToMyRecipeInputData;
import use_cases.add_to_my_recipe.AddToMyRecipeOutputData;
import use_cases.add_to_my_recipe.AddToMyRecipePresenter;

import java.util.List;

public class RecentlyViewedRecipesInteractor implements RecentlyViewedRecipesInputBoundary {
    AddToMyRecipePresenter presenter;
    UserDataAccessInterface userDAO;

    public RecentlyViewedRecipesInteractor(UserDataAccessInterface userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(RecentlyViewedRecipesInputData inputData) {
        User user = inputData.getLoggedInUser();
        List<Recipe> recentlyViewedRecipes = inputData.getRecentlyViewedRecipes();

        LoggedUserData.getLoggedInUser().addRecentlyViewedRecipe(inputData.getNewRecipe());

        // write in DAO
        userDAO.updateRecentlyViewedRecipes(user);
    }
}
