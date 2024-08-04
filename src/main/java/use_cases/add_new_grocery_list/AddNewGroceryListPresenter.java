package use_cases.add_new_grocery_list;

import entity.ShoppingList;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.MyGroceryViewModel;

public class AddNewGroceryListPresenter implements AddNewGroceryListOutputBoundary {
    MyGroceryViewModel viewModel;


    public AddNewGroceryListPresenter(MyGroceryViewModel viewModel) { this.viewModel = viewModel; }

    @Override
    public void prepareSuccessView(PropertyChangeFirer parentModel) {
        viewModel.firePropertyChange("added shoppingList");
        parentModel.firePropertyChange("added shoppingList");
    }
    public void prepareFailureView(String propertyName, PropertyChangeFirer parentViewModel) {
        parentViewModel.firePropertyChange(propertyName);
    }


}
