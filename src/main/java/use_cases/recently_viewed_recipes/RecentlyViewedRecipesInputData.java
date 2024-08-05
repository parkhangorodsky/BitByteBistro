package use_cases.recently_viewed_recipes;

import entity.Recipe;
import entity.User;

import java.util.List;

/**
 * Encapsulates the input data required for updating the recently viewed recipes.
 */
public class RecentlyViewedRecipesInputData {
    private final List<Recipe> recentlyViewedRecipes;
    private final User loggedInUser;
    private final Recipe newRecipe;

    /**
     * Constructs an instance with the specified recently viewed recipes, logged-in user, and new recipe.
     *
     * @param recentlyViewedRecipes The list of recently viewed recipes.
     * @param loggedInUser The currently logged-in user.
     * @param recipe The new recipe to add to the recently viewed list.
     */
    public RecentlyViewedRecipesInputData(List<Recipe> recentlyViewedRecipes, User loggedInUser, Recipe recipe) {
        this.recentlyViewedRecipes = recentlyViewedRecipes;
        this.loggedInUser = loggedInUser;
        this.newRecipe = recipe;
    }

    /**
     * Returns the list of recently viewed recipes.
     *
     * @return The recently viewed recipes.
     */
    public List<Recipe> getRecentlyViewedRecipes() {
        return recentlyViewedRecipes;
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return The logged-in user.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Returns the new recipe to add to the recently viewed list.
     *
     * @return The new recipe.
     */
    public Recipe getNewRecipe() {
        return newRecipe;
    }
}
