package use_cases.display_recipe_detail;

import entity.Recipe;
import entity.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayRecipeDetailViewModelTest {

    private DisplayRecipeDetailViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new DisplayRecipeDetailViewModel("TestView");
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetRecipe() {
        // Arrange
        Recipe testRecipe = mock(Recipe.class);

        // Act
        viewModel.setRecipe(testRecipe);

        // Assert
        assertEquals(testRecipe, viewModel.getRecipe(), "The recipe should be correctly set.");
    }

    @Test
    void testFirePropertyChange() {
        // Arrange
        Recipe testRecipe = mock(Recipe.class);
        viewModel.setRecipe(testRecipe);

        // Act
        viewModel.firePropertyChange("recipeChanged");

        // Assert
        // Verify that the listener was notified with the correct property name and new value
        verify(mockListener).propertyChange(argThat(event ->
                "recipeChanged".equals(event.getPropertyName()) &&
                        testRecipe.equals(event.getNewValue())
        ));
    }

    @Test
    void testAddPropertyChangeListener() {
        // Arrange
        PropertyChangeListener anotherListener = mock(PropertyChangeListener.class);

        // Act
        viewModel.addPropertyChangeListener(anotherListener);
        viewModel.firePropertyChange("testProperty");

        // Assert
        // Verify that the added listener is notified of the property change
        verify(anotherListener).propertyChange(argThat(event ->
                "testProperty".equals(event.getPropertyName())
        ));
    }
}
