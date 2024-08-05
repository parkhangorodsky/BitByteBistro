package use_cases.recently_viewed_recipes;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.util.List;

/**
 * Handles user interactions related to recently viewed recipes and delegates
 * the processing of these interactions to the interactor.
 */
public class RecentlyViewedRecipesController {

    private final RecentlyViewedRecipesInteractor interactor;

    /**
     * Constructs a RecentlyViewedRecipesController with the specified interactor.
     *
     * @param interactor The interactor responsible for processing recently viewed recipes.
     */
    public RecentlyViewedRecipesController(RecentlyViewedRecipesInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the use case for updating recently viewed recipes.
     *
     * @param recipe The recipe to add to the recently viewed list.
     */
    public void execute(Recipe recipe) {
        User user = LoggedUserData.getLoggedInUser();
        if (user != null) {
            List<Recipe> recentlyViewedRecipes = user.getRecentlyViewedRecipes();
            RecentlyViewedRecipesInputData inputData = new RecentlyViewedRecipesInputData(recentlyViewedRecipes, user, recipe);
            interactor.execute(inputData);
        }
    }
}