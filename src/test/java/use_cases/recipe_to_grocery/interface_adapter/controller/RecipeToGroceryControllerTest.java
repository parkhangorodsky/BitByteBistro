package use_cases.recipe_to_grocery.interface_adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.use_case.interactor.RecipeToGroceryInputBoundary;


import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeToGroceryControllerTest {

    private RecipeToGroceryController controller;
    private RecipeToGroceryInputBoundary mockInteractor;



    @BeforeEach
    void setUp() {
        mockInteractor = Mockito.mock(RecipeToGroceryInputBoundary.class);
        controller = new RecipeToGroceryController(mockInteractor);
    }

    @Test
    void testExecute() {
        String testString = "testString";

        // Call the method under test
        controller.convertRecipesToGroceryList(testString);

        // Create an ArgumentCaptor for RecipeToGroceryInputData
        ArgumentCaptor<RecipeToGroceryInputData> argumentCaptor = ArgumentCaptor.forClass(RecipeToGroceryInputData.class);

        // Verify that the execute method of the interactor was called exactly once and capture the argument
        Mockito.verify(mockInteractor, Mockito.times(1)).execute(argumentCaptor.capture());

        // Retrieve the captured argument
        RecipeToGroceryInputData capturedInputData = argumentCaptor.getValue();

        // Assert that the captured argument has the expected query string
        assertEquals(testString, capturedInputData.getUser());
    }
}


