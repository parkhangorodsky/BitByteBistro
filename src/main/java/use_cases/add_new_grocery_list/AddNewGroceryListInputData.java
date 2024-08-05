package use_cases.add_new_grocery_list;

import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

public class AddNewGroceryListInputData {
    private String shoppingListName;
    private PropertyChangeFirer parentModel;

    public AddNewGroceryListInputData(String shoppingListName, PropertyChangeFirer parentModel) {
        this.shoppingListName = shoppingListName;
        this.parentModel = parentModel;
    }

    public String getShoppingListName() { return shoppingListName;}
    public PropertyChangeFirer getParentModel() { return parentModel;}
}
