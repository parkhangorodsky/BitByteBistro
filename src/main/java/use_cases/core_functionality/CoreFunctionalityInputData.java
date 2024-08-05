package use_cases.core_functionality;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.util.List;

public class CoreFunctionalityInputData {
    private Recipe recipe;
    private ShoppingList shoppingList;
    private PropertyChangeFirer parentModel;

    /**
     * Constructs an CoreFunctionalityInputData object with the specified recipe, logged-in user, and parent model.
     *
     * @param recipe       The recipe to be added to the user's grocery list.
     * @param shoppingList the shopping list the recipe will be added to
     * @param parentModel  The model that will be notified of property changes.
     */
    public CoreFunctionalityInputData(Recipe recipe, ShoppingList shoppingList, PropertyChangeFirer parentModel) {
        this.recipe = recipe;
        this.shoppingList = shoppingList;
        this.parentModel = parentModel;
    }

    public Recipe getRecipe() {return recipe;}
    public ShoppingList getShoppingList() {return shoppingList;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
