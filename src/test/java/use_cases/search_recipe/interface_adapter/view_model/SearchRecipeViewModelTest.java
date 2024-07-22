package use_cases.search_recipe.interface_adapter.view_model;

import entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeViewModelTest {

    private SearchRecipeViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    public void setUp() {
        // Initialize the SearchRecipeViewModel with a view name
        viewModel = new SearchRecipeViewModel("SearchRecipeView");

        // Create a mock PropertyChangeListener
        listener = Mockito.mock(PropertyChangeListener.class);

        // Add the mock listener to the viewModel
        viewModel.addPropertyChangeListener(listener);
    }

    /**
     * Tests that the setRecipeSearchResult method correctly sets the search result data.
     */
    @Test
    public void testSetRecipeSearchResult() {
        // Arrange: Create a SearchRecipeOutputData with sample recipes
        Recipe recipe1 = Mockito.mock(Recipe.class);
        Recipe recipe2 = Mockito.mock(Recipe.class);
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(Arrays.asList(recipe1, recipe2));

        // Act: Set the recipe search result in the viewModel
        viewModel.setRecipeSearchResult(outputData);

        // Assert: Verify that the recipe search result is set correctly
        assertEquals(outputData, viewModel.getRecipeSearchResult());
    }

    /**
     * Tests that the firePropertyChanged method correctly notifies listeners of a non-empty search result.
     */
    @Test
    public void testFirePropertyChangedWithResults() {
        // Arrange: Create a SearchRecipeOutputData with sample recipes
        Recipe recipe1 = Mockito.mock(Recipe.class);
        Recipe recipe2 = Mockito.mock(Recipe.class);
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(Arrays.asList(recipe1, recipe2));
        viewModel.setRecipeSearchResult(outputData);

        // Act: Fire property changed event
        viewModel.firePropertyChanged();

        // Assert: Verify that the listener was notified with the correct property change event
        verify(listener, times(1)).propertyChange(argThat(arg ->
                arg.getPropertyName().equals("search recipe") && arg.getNewValue().equals(outputData)
        ));
    }

    /**
     * Tests that the firePropertyChanged method correctly notifies listeners of an empty search result.
     */
    @Test
    public void testFirePropertyChangedWithEmptyResults() {
        // Arrange: Create a SearchRecipeOutputData with no recipes
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(new ArrayList<>());
        viewModel.setRecipeSearchResult(outputData);

        // Act: Fire property changed event
        viewModel.firePropertyChanged();

        // Assert: Verify that the listener was notified with the correct property change event
        verify(listener,times(1)).propertyChange(argThat(event ->
                event.getPropertyName().equals("empty result") && event.getNewValue() == null
        ));
    }
}
