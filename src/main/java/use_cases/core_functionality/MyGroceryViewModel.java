package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.User;
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The `MyGroceryViewModel` class manages the state and behavior of the grocery view.
 * It extends `ViewModel` and implements `PropertyChangeFirer` to support property change
 * notifications for the view.
 */
public class MyGroceryViewModel extends ViewModel implements PropertyChangeFirer {
    private User user;
    private PropertyChangeSupport support;

    /**
     * Constructs a `MyGroceryViewModel` with the specified view name.
     *
     * @param viewName The name of the view associated with this view model.
     */
    public MyGroceryViewModel(String viewName) {
        super(viewName);
        support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a property change listener to this view model.
     *
     * @param listener The listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event to notify listeners of changes to the specified property.
     *
     * @param propertyName The name of the property that has changed.
     */
    @Override
    public void firePropertyChange(String propertyName) {
        this.user = LoggedUserData.getLoggedInUser();
        switch (propertyName) {
            case "grocery":
            case "grocery list already exists":
            case "added shopping list":
                support.firePropertyChange(propertyName, null, user.getShoppingLists().values());
                break;
        }
    }

    /**
     * Gets the user associated with this view model.
     *
     * @return The current user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user for this view model.
     *
     * @param user The user to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}