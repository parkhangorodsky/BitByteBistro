package use_cases;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_cases.display_recipe_detail.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DisplayRecipeDetailInteractor class.
 */
public class DisplayRecipeDetailInteractorTest {

    private DisplayRecipeDetailInteractor interactor;
    private DisplayRecipeDetailPresenter presenter;
    private Recipe recipe;
    private DisplayRecipeDetailViewModel viewModel;

    @BeforeEach
    void setUp() {
        presenter = mock(DisplayRecipeDetailPresenter.class);
        interactor = new DisplayRecipeDetailInteractor(presenter);

        recipe = new Recipe("Test Recipe");
        viewModel = new DisplayRecipeDetailViewModel("Test ViewModel");
    }

    /**
     * Tests executing the interactor with valid input data.
     */
    @Test
    void testExecuteWithValidInput() {
        // Arrange
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(recipe, viewModel);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<DisplayRecipeResultOutputData> outputDataCaptor = ArgumentCaptor.forClass(DisplayRecipeResultOutputData.class);
        verify(presenter).prepareSuccessView(outputDataCaptor.capture());
        DisplayRecipeResultOutputData outputData = outputDataCaptor.getValue();
        assertEquals(recipe, outputData.getRecipe());
        assertEquals(viewModel, outputData.getViewModel());
    }

    /**
     * Tests executing the interactor with a null recipe.
     */
    @Test
    void testExecuteWithNullRecipe() {
        // Arrange
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(null, viewModel);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<DisplayRecipeResultOutputData> outputDataCaptor = ArgumentCaptor.forClass(DisplayRecipeResultOutputData.class);
        verify(presenter).prepareSuccessView(outputDataCaptor.capture());
        DisplayRecipeResultOutputData outputData = outputDataCaptor.getValue();
        assertEquals(null, outputData.getRecipe());
        assertEquals(viewModel, outputData.getViewModel());
    }

    /**
     * Tests executing the interactor with a null view model.
     */
    @Test
    void testExecuteWithNullViewModel() {
        // Arrange
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(recipe, null);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<DisplayRecipeResultOutputData> outputDataCaptor = ArgumentCaptor.forClass(DisplayRecipeResultOutputData.class);
        verify(presenter).prepareSuccessView(outputDataCaptor.capture());
        DisplayRecipeResultOutputData outputData = outputDataCaptor.getValue();
        assertEquals(recipe, outputData.getRecipe());
        assertEquals(null, outputData.getViewModel());
    }

    /**
     * Tests executing the interactor with both null recipe and view model.
     */
    @Test
    void testExecuteWithNullRecipeAndViewModel() {
        // Arrange
        DisplayRecipeDetailInputData inputData = new DisplayRecipeDetailInputData(null, null);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<DisplayRecipeResultOutputData> outputDataCaptor = ArgumentCaptor.forClass(DisplayRecipeResultOutputData.class);
        verify(presenter).prepareSuccessView(outputDataCaptor.capture());
        DisplayRecipeResultOutputData outputData = outputDataCaptor.getValue();
        assertEquals(null, outputData.getRecipe());
        assertEquals(null, outputData.getViewModel());
    }
}
