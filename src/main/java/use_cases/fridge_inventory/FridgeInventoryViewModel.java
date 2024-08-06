package use_cases.fridge_inventory;

import entity.Ingredient;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for managing the state of the fridge inventory in the view.
 * <p>
 * This class encapsulates logic for storing data required to display the
 * FridgeInventoryView and firing property change events upon updates.
 * </p>
 */
public class FridgeInventoryViewModel extends ViewModel {
    private List<Ingredient> ingredients = new ArrayList<>();
    private PropertyChangeSupport support;

    /**
     * Constructs a FridgeInventoryViewModel with the specified view name.
     *
     * @param viewName The name of the view associated with this ViewModel.
     */
    public FridgeInventoryViewModel(String viewName) {
        super(viewName);
        support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a PropertyChangeListener to the ViewModel.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event when the fridge inventory is updated.
     *
     * @param propertyName The name of the property that changed.
     */
    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, null, ingredients);
    }

    /**
     * Returns the list of ingredients in the fridge inventory.
     *
     * @return The list of ingredients.
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the list of ingredients in the fridge inventory and fires a property change event.
     *
     * @param ingredients The new list of ingredients.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        firePropertyChange("update");
    }
}
