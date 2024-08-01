package use_cases.recipe_to_grocery.interface_adapter.view_model;

import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model class for managing data related to converting recipes to a grocery list view.
 * Extends ViewModel to inherit basic view management functionality.
 */
public class RecipeToGroceryViewModel extends ViewModel {

    private RecipeToGroceryOutputData groceryResult;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a RecipeToGroceryViewModel with the specified view name.
     *
     * @param viewName The name of the view associated with this view model.
     */
    public RecipeToGroceryViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Sets the grocery list result data.
     *
     * @param groceryResult The output data containing grocery list information.
     */
    public void setGroceryResult(RecipeToGroceryOutputData groceryResult) {
        this.groceryResult = groceryResult;
    }

    /**
     * Adds a property change listener to observe changes in this view model.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Notifies listeners of property changes, specifically for handling cases where no recipes are available.
     */
    public void firePropertyChanged() {
        if (groceryResult != null && groceryResult.getShoppingLists().isEmpty()) {
            support.firePropertyChange("no recipe", null, null);
            System.out.println("no recipe - view model");
        }
    }
}