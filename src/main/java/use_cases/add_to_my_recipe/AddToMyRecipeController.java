package use_cases.add_to_my_recipe;

import app.local.LoggedUserData;
import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

/**
 * Overview: Controller for adding a recipe to the logged-in user's recipes.
 * Procedure: This class takes the recipe input and parentmodel as argument
 * and store the recipe to the logged in user, and/or display the result in the parent view.
 * Encapsulation: This class encapsulates the logic for passing user input and
 * information about where action was occured to the use case interactor.
 */
public class AddToMyRecipeController {
    AddToMyRecipeInteractor interactor;

    /**
     * Constructor for AddToMyRecipeController
     *
     * @param interactor The interactor responsible for adding recipes to the user's recipes.
     */
    public AddToMyRecipeController(AddToMyRecipeInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the action of adding a recipe to the logged-in user's recipes through the interacrtor.
     * If a user is logged in, this method creates an input data object and passes it to the interactor for execution.
     *
     * @param recipe      The recipe to be added to the user's recipes.
     * @param parentModel The model that will be notified of property changes.
     */
    public void execute(Recipe recipe, PropertyChangeFirer parentModel){
        User user = LoggedUserData.getLoggedInUser();
        if (user != null) {
            AddToMyRecipeInputData inputData = new AddToMyRecipeInputData(recipe, user, parentModel);
            interactor.execute(inputData);
        }

    }

}
