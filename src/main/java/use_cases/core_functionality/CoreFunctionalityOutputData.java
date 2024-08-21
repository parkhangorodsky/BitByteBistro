package use_cases.core_functionality;

import entity.ShoppingList;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

/**
 * The `CoreFunctionalityOutputData` class encapsulates the output data necessary for preparing the
 * success view in core functionality operations. This includes the updated shopping list and the
 * model that will be notified of property changes.
 */
public class CoreFunctionalityOutputData {
    private ShoppingList shoppingList;
    private PropertyChangeFirer parentModel;

    /**
     * Constructs a `CoreFunctionalityOutputData` object with the specified shopping list and parent model.
     *
     * @param shoppingList The shopping list to which the recipe has been added. This represents the updated
     *                     shopping list after performing core functionality operations.
     * @param parentModel  The model that will be notified of property changes. It is used to update
     *                     the view with the new state after the operation.
     */
    public CoreFunctionalityOutputData(ShoppingList shoppingList, PropertyChangeFirer parentModel) {
        this.shoppingList = shoppingList;
        this.parentModel = parentModel;
    }

    /**
     * Returns the updated shopping list.
     *
     * @return The shopping list to which the recipe has been added.
     */
    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    /**
     * Returns the model that will be notified of property changes.
     *
     * @return The model that will be updated with the new state after the operation.
     */
    public PropertyChangeFirer getParentModel() {
        return parentModel;
    }
}
