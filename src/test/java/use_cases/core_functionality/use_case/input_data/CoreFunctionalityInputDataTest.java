package use_cases.core_functionality.use_case.input_data;

import entity.Recipe;
import entity.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases.core_functionality.CoreFunctionalityInputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for the CoreFunctionalityInputData class.
 *
 * These tests cover the functionality of the class, ensuring that
 * its constructors and getter methods behave as expected.
 */
class CoreFunctionalityInputDataTest {

    private Recipe recipe;
    private ShoppingList shoppingList;
    private PropertyChangeFirer parentModel;
    private CoreFunctionalityInputData inputData;

    /**
     * Setup method that runs before each test.
     *
     * This method initializes the mock objects for Recipe, ShoppingList,
     * and PropertyChangeFirer, and creates an instance of CoreFunctionalityInputData.
     */
    @BeforeEach
    void setUp() {
        // Create mock objects for the dependencies
        recipe = mock(Recipe.class);
        shoppingList = mock(ShoppingList.class);
        parentModel = mock(PropertyChangeFirer.class);

        // Initialize the CoreFunctionalityInputData with mock dependencies
        inputData = new CoreFunctionalityInputData(recipe, shoppingList, parentModel);
    }

    /**
     * Tests that the getRecipe method returns the correct recipe object.
     */
    @Test
    void testGetRecipe() {
        assertEquals(recipe, inputData.getRecipe(), "The returned recipe should match the one provided.");
    }

    /**
     * Tests that the getShoppingList method returns the correct shopping list object.
     */
    @Test
    void testGetShoppingList() {
        assertEquals(shoppingList, inputData.getShoppingList(), "The returned shopping list should match the one provided.");
    }

    /**
     * Tests that the getParentModel method returns the correct parent model object.
     */
    @Test
    void testGetParentModel() {
        assertEquals(parentModel, inputData.getParentModel(), "The returned parent model should match the one provided.");
    }
}