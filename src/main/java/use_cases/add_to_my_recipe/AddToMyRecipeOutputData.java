package use_cases.add_to_my_recipe;

import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

/**
 * Overview: Data structure that represents data for the use case of adding a recipe to the logged-in user's recipes.
 * Encapsulation: This class encapsulates the necessary information to be passed to the presenter for preparing the view.
 */
public class AddToMyRecipeOutputData {
    User user;
    PropertyChangeFirer parentModel;

    /**
     * Constructor for AddToMyRecipeOutputData
     *
     * @param user        The user to whom the recipe has been added.
     * @param parentModel The model that will be notified of property changes.
     */
    public AddToMyRecipeOutputData(User user, PropertyChangeFirer parentModel) {
        this.user = user;
        this.parentModel = parentModel;
    }

    public User getUser() {return user;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
