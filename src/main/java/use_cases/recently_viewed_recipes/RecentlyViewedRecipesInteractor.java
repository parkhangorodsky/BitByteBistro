package use_cases.recently_viewed_recipes;

import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import frameworks.data_access.UserDataAccessInterface;
import use_cases.add_to_my_recipe.AddToMyRecipeInputData;
import use_cases.add_to_my_recipe.AddToMyRecipeOutputData;
import use_cases.add_to_my_recipe.AddToMyRecipePresenter;

import java.util.List;

/**
 * Handles the business logic for updating the recently viewed recipes of a user.
 * <p>
 * This interactor processes the input data, updates the user's recently viewed recipes,
 * and persists the changes using the data access object (DAO).
 * </p>
 */
public class RecentlyViewedRecipesInteractor implements RecentlyViewedRecipesInputBoundary {
    private final UserDataAccessInterface userDAO;

    /**
     * Constructs a RecentlyViewedRecipesInteractor with the specified user data access object.
     *
     * @param userDAO The data access object used to persist changes to the user's recently viewed recipes.
     */
    public RecentlyViewedRecipesInteractor(UserDataAccessInterface userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Executes the use case to update the recently viewed recipes.
     * <p>
     * This method adds the new recipe to the user's recently viewed list and updates
     * the data in the data access layer.
     * </p>
     *
     * @param inputData The input data containing the user's current state and the new recipe.
     */
    @Override
    public void execute(RecentlyViewedRecipesInputData inputData) {
        User user = inputData.getLoggedInUser();
        List<Recipe> recentlyViewedRecipes = inputData.getRecentlyViewedRecipes();

        LoggedUserData.getLoggedInUser().addRecentlyViewedRecipe(inputData.getNewRecipe());

        // Persist the updated recently viewed recipes in the data access layer
        userDAO.updateRecentlyViewedRecipes(user);
    }
}