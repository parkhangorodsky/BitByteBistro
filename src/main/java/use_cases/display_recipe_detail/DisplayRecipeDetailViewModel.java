package use_cases.display_recipe_detail;

import entity.Recipe;
import entity.ShoppingList;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class responsible for managing and providing detailed recipe information to the view.
 * This class extends {@code ViewModel} and implements {@code PropertyChangeFirer} to support property change events.
 */
public class DisplayRecipeDetailViewModel extends ViewModel implements PropertyChangeFirer {
    private Recipe recipe;
    private ShoppingList shoppingList;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new {@code DisplayRecipeDetailViewModel} with the specified view name.
     *
     * @param viewName The name of the view associated with this view model.
     */
    public DisplayRecipeDetailViewModel(String viewName) {
        super(viewName);
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Fires a property change event to notify listeners of changes to the specified property.
     *
     * @param propertyName The name of the property that has changed.
     */
    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, null, this.recipe);
    }

    /**
     * Adds a property change listener to this view model.
     *
     * @param listener The {@code PropertyChangeListener} to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ShoppingList getShoppingList() {return shoppingList;}
}
