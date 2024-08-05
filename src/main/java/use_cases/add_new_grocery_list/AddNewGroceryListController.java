package use_cases.add_new_grocery_list;

import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

public class AddNewGroceryListController {
    AddNewGroceryListInteractor addNewGroceryListInteractor;

    public AddNewGroceryListController(AddNewGroceryListInteractor addNewGroceryListInteractor) {
        this.addNewGroceryListInteractor = addNewGroceryListInteractor;
    }
    public void execute(String shoppingListName, PropertyChangeFirer parentModel){
        AddNewGroceryListInputData inputData = new AddNewGroceryListInputData(shoppingListName, parentModel);
        addNewGroceryListInteractor.execute(inputData);
    }
}
