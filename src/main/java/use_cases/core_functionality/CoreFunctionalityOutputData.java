package use_cases.core_functionality;

import entity.ShoppingList;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

public class CoreFunctionalityOutputData {
    ShoppingList shoppingList;
    PropertyChangeFirer parentModel;

    /**
     * Constructor for CoreFunctionalityOutputData
     *
     * @param shoppingList        the shoppinglist to whom the recipe has been added.
     * @param parentModel The model that will be notified of property changes.
     */
    public CoreFunctionalityOutputData(ShoppingList shoppingList, PropertyChangeFirer parentModel) {
        this.shoppingList = shoppingList;
        this.parentModel = parentModel;
    }

    public ShoppingList getShoppingList() {return shoppingList;}
    public PropertyChangeFirer getParentModel() {return parentModel;}
}
