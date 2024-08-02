package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

public class AddToMyRecipeInputData {

    private Recipe recipe;
    private User loggedInUser;
    private PropertyChangeFirer parentModel;

    public AddToMyRecipeInputData(Recipe recipe, User loggedInUser, PropertyChangeFirer parentModel) {
        this.recipe = recipe;
        this.loggedInUser = loggedInUser;
        this.parentModel = parentModel;
    }

    public Recipe getRecipe() {return recipe;}
    public User getLoggedInUser() {return loggedInUser;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
