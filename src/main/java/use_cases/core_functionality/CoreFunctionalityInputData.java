package use_cases.core_functionality;

import entity.Ingredient;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import java.util.List;

/**
 * The `CoreFunctionalityInputData` class encapsulates the data required for executing core functionality operations.
 * This includes the recipe to be processed, the shopping list to which the recipe will be added,
 * and the model responsible for notifying property changes.
 */
public class CoreFunctionalityInputData {

    private Recipe recipe;
    private ShoppingList shoppingList;
    private PropertyChangeFirer parentModel;

    /**
     * Constructs a `CoreFunctionalityInputData` object with the specified recipe, shopping list, and parent model.
     *
     * @param recipe       The recipe to be processed and potentially added to the shopping list.
     * @param shoppingList The shopping list to which the recipe's ingredients will be added.
     * @param parentModel  The model that will be notified of property changes.
     */
    public CoreFunctionalityInputData(Recipe recipe, ShoppingList shoppingList, PropertyChangeFirer parentModel) {
        this.recipe = recipe;
        this.shoppingList = shoppingList;
        this.parentModel = parentModel;
    }

    /**
     * Returns the recipe associated with this input data.
     *
     * @return The recipe to be processed.
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Returns the shopping list associated with this input data.
     *
     * @return The shopping list to which ingredients will be added.
     */
    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    /**
     * Returns the model responsible for notifying property changes.
     *
     * @return The model that will be notified of property changes.
     */
    public PropertyChangeFirer getParentModel() {
        return parentModel;
    }
}
