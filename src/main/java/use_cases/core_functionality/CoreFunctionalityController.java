package use_cases.core_functionality;

import entity.Ingredient;
import entity.LoggedUserData;
import entity.Recipe;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.util.List;

/**
 * Overview: Controller for adding a recipe to the logged-in user's grocery list.
 * Procedure: This class takes the recipe input and parentmodel as argument
 * and stores the recipe to the logged in user, and/or display the result in the parent view.
 * Encapsulation: This class encapsulates the logic for passing user input and
 * information about where action occured to the use case interactor.
 */
public class CoreFunctionalityController {
    CoreFunctionalityInteractor interactor;

    /**
     * Constructor for CoreFunctionalityController
     *
     * @param interactor The interactor responsible for adding recipes to the user's grocery list.
     */
    public CoreFunctionalityController(CoreFunctionalityInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the action of adding a recipe to the logged-in user's grocery list through the interactor.
     * If a user is logged in, this method creates an input data object and passes it to the interactor for execution.
     *
     * @param recipe      The recipe to be added to the user's recipes.
     * @param parentModel The model that will be notified of property changes.
     */
    public void execute(Recipe recipe, PropertyChangeFirer parentModel){
        List<Ingredient> newIngredients = recipe.getIngredientList();
        User user = LoggedUserData.getLoggedInUser();
        // for now, just works for one shopping list

        if (user != null) {
            CoreFunctionalityInputData inputData = new CoreFunctionalityInputData(recipe, user, newIngredients, parentModel);
            interactor.execute(inputData);
        }

    }

}
