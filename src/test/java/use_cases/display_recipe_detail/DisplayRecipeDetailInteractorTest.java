package use_cases.display_recipe_detail;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayRecipeDetailInteractorTest {

    private DisplayRecipeDetailOutputBoundary presenter;
    private DisplayRecipeDetailInteractor interactor;

    @BeforeEach
    void setUp() {
        presenter = mock(DisplayRecipeDetailOutputBoundary.class);
        interactor = new DisplayRecipeDetailInteractor(presenter);
    }

    @Test
    void testExecute() {
        // Arrange
        Recipe testRecipe = mock(Recipe.class);
        DisplayRecipeDetailViewModel testViewModel = new DisplayRecipeDetailViewModel("display");
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(testRecipe, testViewModel);

        // Act
        interactor.execute(inputData);

        // Capture the argument passed to prepareSuccessView
        ArgumentCaptor<DisplayRecipeResultOutputData> captor = ArgumentCaptor.forClass(DisplayRecipeResultOutputData.class);
        verify(presenter).prepareSuccessView(captor.capture());

        // Assert
        DisplayRecipeResultOutputData capturedOutputData = captor.getValue();
        assertEquals(testRecipe, capturedOutputData.getRecipe(), "The recipe should match the one provided in the input data.");
        assertEquals(testViewModel, capturedOutputData.getViewModel(), "The viewModel should match the one provided in the input data.");
    }
}
