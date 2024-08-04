package use_cases.search_recipe.interface_adapter.view_model;

import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Overview: View model for the search recipe use case.
 * Procedure: This class holds the search results and notifies listeners about changes to the search results.
 * Encapsulation: This class encapsulates logic for notifying updates on search result.
 */
public class SearchRecipeViewModel extends ViewModel {

    private SearchRecipeOutputData recipeSearchResult;
    /**
     * Manages the list of listeners and dispatches property change events to them.
     * <p>
     * The PropertyChangeSupport class is used to provide support for managing
     * property change listeners and firing property change events.
     * </p>
     * <p>
     * The `PropertyChangeSupport` object will be used to:
     * - Register property change listeners.
     * - Remove property change listeners.
     * - Fire property change events to notify all registered listeners of changes in properties.
     * </p>
     * <p>
     * Example of usage:
     * - `support.addPropertyChangeListener(listener)`: Adds a listener to be notified of property changes.
     * - `support.firePropertyChange(propertyName, oldValue, newValue)`: Fires a property change event to all registered listeners.
     * </p>
     * This mechanism is crucial for implementing the observer pattern, where multiple objects (listeners)
     * need to be informed about changes in another object (the observable).
     */
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a SearchRecipeViewModel with the specified view name.
     *
     * @param viewName the name of the view associated with this view model
     */
    public SearchRecipeViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Sets the search result data.
     *
     * @param recipeSearchResult the search results data
     */
    public void setRecipeSearchResult(SearchRecipeOutputData recipeSearchResult) {
        this.recipeSearchResult = recipeSearchResult;
    }
    public SearchRecipeOutputData getRecipeSearchResult() {
        return recipeSearchResult;
    }

    /**
     * Notifies all registered listeners that the search results have changed.
     * Fires a property change event with "search recipe" if there are results, otherwise fires "empty result".
     */
    public void firePropertyChange() {
        if (!recipeSearchResult.getRecipes().isEmpty()) {
            support.firePropertyChange("search recipe", null, this.recipeSearchResult);
        } else {
            support.firePropertyChange("empty result", null, null);
        }
    }

    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName,null,  null);
    }


    /**
     * Adds a property change listener to this view model.
     *
     * @param listener the property change listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
