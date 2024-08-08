package use_cases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_new_grocery_list.AddNewGroceryListInputData;
import use_cases.add_new_grocery_list.AddNewGroceryListInteractor;

import static org.mockito.Mockito.*;

public class AddNewGroceryListControllerTest {

    private AddNewGroceryListController controller;
    private AddNewGroceryListInteractor interactor;
    private PropertyChangeFirer parentModel;

    @BeforeEach
    public void setUp() {
        interactor = mock(AddNewGroceryListInteractor.class);
        controller = new AddNewGroceryListController(interactor);
        parentModel = mock(PropertyChangeFirer.class);
    }

    @Test
    public void testExecute() {
        String shoppingListName = "Weekly Groceries";

        controller.execute(shoppingListName, parentModel);

        verify(interactor).execute(any(AddNewGroceryListInputData.class));
    }
}
