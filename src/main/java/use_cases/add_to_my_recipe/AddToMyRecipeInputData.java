package use_cases.add_to_my_recipe;

import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

/**
 * Overview: Data structure that represents the input data for add to my recipe use case.
 * Encapsulation: This class encapsulates the data and access needed for add to my recipe use case.
 */
public class AddToMyRecipeInputData {

    private Recipe recipe;
    private User loggedInUser;
    private PropertyChangeFirer parentModel;

    /**
     * Constructs an AddToMyRecipeInputData object with the specified recipe, logged-in user, and parent model.
     *
     * @param recipe       The recipe to be added to the user's recipes.
     * @param loggedInUser The currently logged-in user.
     * @param parentModel  The model that will be notified of property changes.
     */
    public AddToMyRecipeInputData(Recipe recipe, User loggedInUser, PropertyChangeFirer parentModel) {
        this.recipe = recipe;
        this.loggedInUser = loggedInUser;
        this.parentModel = parentModel;
    }

    public Recipe getRecipe() {return recipe;}
    public User getLoggedInUser() {return loggedInUser;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
