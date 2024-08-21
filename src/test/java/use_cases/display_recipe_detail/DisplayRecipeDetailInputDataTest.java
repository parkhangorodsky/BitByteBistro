package use_cases.display_recipe_detail;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class DisplayRecipeDetailInputDataTest {

    private Recipe testRecipe;
    private DisplayRecipeDetailViewModel testViewModel;
    private DisplayRecipeDetailInputData inputData;

    @BeforeEach
    void setUp() {
        testRecipe = mock(Recipe.class);
        testViewModel = new DisplayRecipeDetailViewModel("display_recipe_detail");
        inputData = new DisplayRecipeDetailInputData(testRecipe, testViewModel);
    }

    @Test
    void testGetRecipe() {
        // Assert
        assertEquals(testRecipe, inputData.getRecipe(), "The recipe should match the one provided in the constructor.");
    }

    @Test
    void testGetViewModel() {
        // Assert
        assertEquals(testViewModel, inputData.getViewModel(), "The viewModel should match the one provided in the constructor.");
    }
}
