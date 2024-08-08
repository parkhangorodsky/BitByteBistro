package use_cases;

import entity.Recipe;
import entity.ShoppingList;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.CoreFunctionalityInputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoreFunctionalityInputDataTest {

    @Test
    public void testCoreFunctionalityInputData() {
        Recipe testRecipe = mock(Recipe.class);
        ShoppingList testShoppingList = mock(ShoppingList.class);
        PropertyChangeFirer parentModel = mock(PropertyChangeFirer.class);

        CoreFunctionalityInputData inputData = new CoreFunctionalityInputData(testRecipe, testShoppingList, parentModel);

        assertEquals(testRecipe, inputData.getRecipe());
        assertEquals(testShoppingList, inputData.getShoppingList());
        assertEquals(parentModel, inputData.getParentModel());
    }
}
