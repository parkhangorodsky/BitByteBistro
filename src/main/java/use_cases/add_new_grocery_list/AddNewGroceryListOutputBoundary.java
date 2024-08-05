package use_cases.add_new_grocery_list;

import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

public interface AddNewGroceryListOutputBoundary {
    void prepareSuccessView(PropertyChangeFirer parentModel);
}
