package use_cases.core_functionality;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.util.List;

public class CoreFunctionalityInputData {
    private Recipe recipe;
    private User loggedInUser;
    private List<Ingredient> shoppingListIngredients;
    private PropertyChangeFirer parentModel;

    /**
     * Constructs an CoreFunctionalityInputData object with the specified recipe, logged-in user, and parent model.
     *
     * @param recipe       The recipe to be added to the user's recipes.
     * @param loggedInUser The currently logged-in user.
     * @param shoppingListIngredients the shopping list's current list of ingredients
     * @param parentModel  The model that will be notified of property changes.
     */
    public CoreFunctionalityInputData(Recipe recipe, User loggedInUser, List<Ingredient> shoppingListIngredients, PropertyChangeFirer parentModel) {
        this.recipe = recipe;
        this.loggedInUser = loggedInUser;
        this.shoppingListIngredients = shoppingListIngredients;
        this.parentModel = parentModel;
    }

    public Recipe getRecipe() {return recipe;}
    public User getLoggedInUser() {return loggedInUser;}
    public List<Ingredient> getShoppingListIngredients() {return shoppingListIngredients;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
