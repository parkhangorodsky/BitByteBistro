package use_cases.add_new_grocery_list;

import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddNewGroceryListInputDataTest {

    @Test
    public void testAddNewGroceryListInputData() {
        String shoppingListName = "Weekly Groceries";
        PropertyChangeFirer parentModel = mock(PropertyChangeFirer.class);

        AddNewGroceryListInputData inputData = new AddNewGroceryListInputData(shoppingListName, parentModel);

        assertEquals(shoppingListName, inputData.getShoppingListName());
        assertEquals(parentModel, inputData.getParentModel());
    }
}
