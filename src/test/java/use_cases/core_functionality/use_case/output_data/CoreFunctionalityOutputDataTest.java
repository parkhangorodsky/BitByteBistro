package use_cases.core_functionality.use_case.output_data;

import entity.ShoppingList;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.core_functionality.CoreFunctionalityOutputData;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CoreFunctionalityOutputData class.
 * This class tests the behavior of CoreFunctionalityOutputData's methods
 * to ensure that it properly encapsulates and returns the ShoppingList and
 * PropertyChangeFirer objects.
 */
class CoreFunctionalityOutputDataTest {
    private CoreFunctionalityOutputData outputData;
    private ShoppingList mockShoppingList;
    private PropertyChangeFirer mockParentModel;

    /**
     * Sets up the test environment before each test.
     * Mocks the dependencies (ShoppingList and PropertyChangeFirer)
     * and initializes the CoreFunctionalityOutputData object.
     */
    @BeforeEach
    void setUp() {
        mockShoppingList = Mockito.mock(ShoppingList.class);
        mockParentModel = Mockito.mock(PropertyChangeFirer.class);
        outputData = new CoreFunctionalityOutputData(mockShoppingList, mockParentModel);
    }

    /**
     * Tests the getShoppingList() method.
     * Verifies that the method returns the correct ShoppingList object.
     */
    @Test
    void getShoppingList() {
        assertEquals(mockShoppingList, outputData.getShoppingList(),
                "getShoppingList() should return the ShoppingList object passed to the constructor.");
    }

    /**
     * Tests the getParentModel() method.
     * Verifies that the method returns the correct PropertyChangeFirer object.
     */
    @Test
    void getParentModel() {
        assertEquals(mockParentModel, outputData.getParentModel(),
                "getParentModel() should return the PropertyChangeFirer object passed to the constructor.");
    }
}