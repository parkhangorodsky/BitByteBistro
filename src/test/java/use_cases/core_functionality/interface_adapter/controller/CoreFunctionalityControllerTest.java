package use_cases.core_functionality.interface_adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.core_functionality.CoreFunctionalityInputData;
import use_cases.core_functionality.CoreFunctionalityInputBoundary;

import entity.Recipe;
import entity.ShoppingList;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

class CoreFunctionalityControllerTest {

    private CoreFunctionalityController controller;
    private CoreFunctionalityInputBoundary mockInteractor;

    @BeforeEach
    void setUp() {
        mockInteractor = Mockito.mock(CoreFunctionalityInputBoundary.class);
        controller = new CoreFunctionalityController(mockInteractor);
    }

    @Test
    void testExecute() {
        // Arrange
        ShoppingList mockShoppingList = Mockito.mock(ShoppingList.class);
        Recipe mockRecipe = Mockito.mock(Recipe.class);
        PropertyChangeFirer mockParentModel = Mockito.mock(PropertyChangeFirer.class);

        // Act
        controller.execute(mockShoppingList, mockRecipe, mockParentModel);

        // Create an ArgumentCaptor for CoreFunctionalityInputData
        ArgumentCaptor<CoreFunctionalityInputData> argumentCaptor = ArgumentCaptor.forClass(CoreFunctionalityInputData.class);

        // Verify that the execute method of the interactor was called exactly once and capture the argument
        Mockito.verify(mockInteractor, times(1)).execute(argumentCaptor.capture());

        // Retrieve the captured argument
        CoreFunctionalityInputData capturedInputData = argumentCaptor.getValue();

        // Assert that the captured argument is not null
        assertNotNull(capturedInputData);

        // Assert that the captured argument contains the expected shopping list, recipe, and parent model
        assertEquals(mockShoppingList, capturedInputData.getShoppingList());
        assertEquals(mockRecipe, capturedInputData.getRecipe());
        assertEquals(mockParentModel, capturedInputData.getParentModel());
    }
}