package use_cases.add_to_my_recipe;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyRecipeViewModelTest {

    private MyRecipeViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new MyRecipeViewModel("TestView");
        mockListener = mock(PropertyChangeListener.class);
    }

    @Test
    void testAddPropertyChangeListener() {
        // Act
        viewModel.addPropertyChangeListener(mockListener);

        // Assert
        // No direct assert here as we are testing side effects.
        // We will validate this indirectly in the firePropertyChange test.
    }

    @Test
    void testFirePropertyChange() {
        // Arrange
        viewModel.addPropertyChangeListener(mockListener);
        List<Recipe> recipes = new ArrayList<>();
        viewModel.setRecipes(recipes);

        // Act
        viewModel.firePropertyChange("recipes");

        // Assert
        ArgumentCaptor<PropertyChangeEvent> captor = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(mockListener).propertyChange(captor.capture());

        assertEquals("recipes", captor.getValue().getPropertyName());
        assertEquals(recipes, captor.getValue().getNewValue());
    }

    @Test
    void testGetAndSetRecipes() {
        // Arrange
        List<Recipe> expectedRecipes = new ArrayList<>();
        Recipe recipe = mock(Recipe.class);
        expectedRecipes.add(recipe);

        // Act
        viewModel.setRecipes(expectedRecipes);
        List<Recipe> actualRecipes = viewModel.getRecipes();

        // Assert
        assertEquals(expectedRecipes, actualRecipes);
    }
}
