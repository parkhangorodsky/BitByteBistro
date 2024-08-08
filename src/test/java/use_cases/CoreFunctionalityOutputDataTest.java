package use_cases;

import entity.ShoppingList;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.CoreFunctionalityOutputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoreFunctionalityOutputDataTest {

    @Test
    public void testCoreFunctionalityOutputData() {
        ShoppingList testShoppingList = mock(ShoppingList.class);
        PropertyChangeFirer parentModel = mock(PropertyChangeFirer.class);

        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(testShoppingList, parentModel);

        assertEquals(testShoppingList, outputData.getShoppingList());
        assertEquals(parentModel, outputData.getParentModel());
    }
}
