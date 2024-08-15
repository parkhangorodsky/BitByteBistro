package use_cases.core_functionality;

import entity.Recipe;
import entity.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

/**
 * Unit tests for the CoreFunctionalityController class.
 * These tests ensure that the CoreFunctionalityController behaves as expected
 * when interacting with its interactor and handling input data.
 */
class CoreFunctionalityControllerTest {

    // The controller instance under test
    private CoreFunctionalityController controller;

    // Mocked interactor that will be used to verify the controller's behavior
    private CoreFunctionalityInputBoundary mockInteractor;

    /**
     * Setup method that runs before each test.
     * Initializes the controller and mocks the interactor.
     */
    @BeforeEach
    void setUp() {
        mockInteractor = Mockito.mock(CoreFunctionalityInputBoundary.class);
        controller = new CoreFunctionalityController(mockInteractor);
    }

    /**
     * Test for the execute method in CoreFunctionalityController.
     * Verifies that the controller correctly constructs CoreFunctionalityInputData
     * and passes it to the interactor for execution.
     */
    @Test
    void testExecute() {
        // Arrange: Create mock objects for the shopping list, recipe, and parent model.
        ShoppingList mockShoppingList = Mockito.mock(ShoppingList.class);
        Recipe mockRecipe = Mockito.mock(Recipe.class);
        PropertyChangeFirer mockParentModel = Mockito.mock(PropertyChangeFirer.class);

        // Act: Call the execute method of the controller.
        controller.execute(mockShoppingList, mockRecipe, mockParentModel);

        // Create an ArgumentCaptor to capture the input data passed to the interactor's execute method.
        ArgumentCaptor<CoreFunctionalityInputData> argumentCaptor = ArgumentCaptor.forClass(CoreFunctionalityInputData.class);

        // Assert: Verify that the interactor's execute method was called once with the correct input data.
        Mockito.verify(mockInteractor, times(1)).execute(argumentCaptor.capture());
        CoreFunctionalityInputData capturedInputData = argumentCaptor.getValue();

        // Assert: Verify that the captured input data is not null and contains the expected values.
        assertNotNull(capturedInputData);
        assertEquals(mockShoppingList, capturedInputData.getShoppingList());
        assertEquals(mockRecipe, capturedInputData.getRecipe());
        assertEquals(mockParentModel, capturedInputData.getParentModel());
    }
}