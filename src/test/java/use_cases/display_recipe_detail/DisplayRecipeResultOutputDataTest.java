package use_cases.display_recipe_detail;

import entity.Recipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class DisplayRecipeResultOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Recipe testRecipe = mock(Recipe.class);
        DisplayRecipeDetailViewModel testViewModel = new DisplayRecipeDetailViewModel("TestView");

        // Act
        DisplayRecipeResultOutputData outputData = new DisplayRecipeResultOutputData(testRecipe, testViewModel);

        // Assert
        assertEquals(testRecipe, outputData.getRecipe(), "The recipe should be correctly set.");
        assertEquals(testViewModel, outputData.getViewModel(), "The view model should be correctly set.");
    }
}
