package use_cases.recently_viewed_recipes;

import entity.Recipe;
import entity.User;

import java.util.List;

public class RecentlyViewedRecipesInputData {
    private List<Recipe> recentlyViewedRecipes;
    private User loggedInUser;
    private Recipe newRecipe;

    public RecentlyViewedRecipesInputData(List<Recipe> recentlyViewedRecipes, User loggedInUser, Recipe recipe) {
        this.recentlyViewedRecipes = recentlyViewedRecipes;
        this.loggedInUser = loggedInUser;
        this.newRecipe = recipe;
    }

    public List<Recipe> getRecentlyViewedRecipes() {return recentlyViewedRecipes;}
    public User getLoggedInUser() {return loggedInUser;}
    public Recipe getNewRecipe() {return newRecipe;}
}
