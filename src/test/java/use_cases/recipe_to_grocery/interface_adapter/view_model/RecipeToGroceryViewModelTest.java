package use_cases.recipe_to_grocery.interface_adapter.view_model;

import entity.Recipe;
import entity.ShoppingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_cases.recipe_to_grocery.interface_adapter.view_model.RecipeToGroceryViewModel;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeToGroceryViewModelTest {

    private RecipeToGroceryViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    public void setUp() {
        // Initialize the RecipeToGroceryViewModel with a view name
        viewModel = new RecipeToGroceryViewModel("RecipeToGroceryView");

        // Create a mock PropertyChangeListener
        listener = Mockito.mock(PropertyChangeListener.class);

        // Add the mock listener to the viewModel
        viewModel.addPropertyChangeListener(listener);
    }

    /**
     * Tests that the setGroceryResult method correctly sets the shopping list result data.
     */
    @Test
    public void testSetRecipeSearchResult() {
        // Arrange: Create a RecipeToGroceryOutputData with sample recipes
        ShoppingList shoppingList1 = Mockito.mock(ShoppingList.class);
        ShoppingList shoppingList2 = Mockito.mock(ShoppingList.class);
        RecipeToGroceryOutputData outputData = new RecipeToGroceryOutputData(Arrays.asList(shoppingList1, shoppingList2));
        

        // Act: Set the shopping list result in the viewModel
        viewModel.setGroceryResult(outputData);
    }

    /**
     * Tests that the firePropertyChanged method correctly notifies listeners of a non-empty shopping list.
     */
    @Test
    public void testFirePropertyChangedWithResults() {
        // Arrange: Create a RecipeToGroceryOutputData with sample recipes
        ShoppingList shoppingList1 = Mockito.mock(ShoppingList.class);
        ShoppingList shoppingList2 = Mockito.mock(ShoppingList.class);
        RecipeToGroceryOutputData outputData = new RecipeToGroceryOutputData(Arrays.asList(shoppingList1, shoppingList2));
        viewModel.setGroceryResult(outputData);

        // Act: Fire property changed event
        viewModel.firePropertyChanged();

        // Assert: Verify that the listener was notified with the correct property change event
        verify(listener, times(1)).propertyChange(argThat(arg ->
                arg.getPropertyName().equals("recipes") && arg.getNewValue().equals(outputData)
        ));
    }

    /**
     * Tests that the firePropertyChanged method correctly notifies listeners of an empty shopping list.
     */
    @Test
    public void testFirePropertyChangedWithEmptyResults() {
        // Arrange: Create a RecipeToGroceryOutputData with no recipes
        RecipeToGroceryOutputData outputData = new RecipeToGroceryOutputData(new ArrayList<>());
        viewModel.setGroceryResult(outputData);

        // Act: Fire property changed event
        viewModel.firePropertyChanged();

        // Assert: Verify that the listener was notified with the correct property change event
        verify(listener,times(1)).propertyChange(argThat(event ->
                event.getPropertyName().equals("no recipe") && event.getNewValue() == null
        ));
    }
}
