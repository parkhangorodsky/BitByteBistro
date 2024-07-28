package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;

public class AddToMyRecipeInputData {

    private Recipe recipe;
    private User loggedInUser;

    public AddToMyRecipeInputData(Recipe recipe, User loggedInUser) {
        this.recipe = recipe;
        this.loggedInUser = loggedInUser;
    }

    public Recipe getRecipe() {return recipe;}
    public User getLoggedInUser() {return loggedInUser;}
}
