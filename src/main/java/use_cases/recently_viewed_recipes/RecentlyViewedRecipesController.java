package use_cases.recently_viewed_recipes;

import app.local.LoggedUserData;
import entity.Recipe;
import entity.User;

import java.util.List;

public class RecentlyViewedRecipesController {

    RecentlyViewedRecipesInteractor interactor;

    public RecentlyViewedRecipesController(RecentlyViewedRecipesInteractor interactor) {
        this.interactor = interactor;
    }

    public void execute(Recipe recipe){
        User user = LoggedUserData.getLoggedInUser();
        List<Recipe> recentlyViewedRecipes = user.getRecentlyViewedRecipes();
        if (user != null) {
            RecentlyViewedRecipesInputData inputData = new RecentlyViewedRecipesInputData(recentlyViewedRecipes, user, recipe);
            interactor.execute(inputData);
        }

    }
}
