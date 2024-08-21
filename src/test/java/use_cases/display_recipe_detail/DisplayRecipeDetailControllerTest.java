package use_cases.display_recipe_detail;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayRecipeDetailControllerTest {

    private DisplayRecipeDetailController controller;
    private DisplayRecipeDetailInputBoundary mockInteractor;
    private Recipe testRecipe;
    private DisplayRecipeDetailViewModel testViewModel;

    @BeforeEach
    void setUp() {
        mockInteractor = mock(DisplayRecipeDetailInputBoundary.class);
        controller = new DisplayRecipeDetailController(mockInteractor);
        testRecipe = mock(Recipe.class);
        testViewModel = new DisplayRecipeDetailViewModel("display");
    }

    @Test
    void testExecute() {
        // Act
        controller.execute(testRecipe, testViewModel);

        // Capture the inputData argument passed to the interactor's execute method
        ArgumentCaptor<DisplayRecipeDetailInputData> captor = ArgumentCaptor.forClass(DisplayRecipeDetailInputData.class);
        verify(mockInteractor).execute(captor.capture());

        // Assert
        DisplayRecipeDetailInputData capturedInputData = captor.getValue();
        assertEquals(testRecipe, capturedInputData.getRecipe());
        assertEquals(testViewModel, capturedInputData.getViewModel());
    }
}
