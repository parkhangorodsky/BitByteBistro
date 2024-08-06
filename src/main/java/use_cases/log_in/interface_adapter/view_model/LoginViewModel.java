package use_cases.log_in.interface_adapter.view_model;

import entity.User;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;
import use_cases.log_in.use_case.output_data.LoginOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state of the login view.
 * This class extends the ViewModel class and uses PropertyChangeSupport to notify listeners
 * of changes to its properties, specifically the errorMessage property.
 */
public class LoginViewModel extends ViewModel {
    private String errorMessage;
    private final PropertyChangeSupport support;

    /**
     * Constructs a new LoginViewModel with the specified view name.
     *
     * @param viewName The name of the view.
     */
    public LoginViewModel(String viewName) {
        super(viewName);  // Call to the parameterized constructor of the superclass
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a property change listener to the view model.
     *
     * @param pcl The property change listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * Removes a property change listener from the view model.
     *
     * @param pcl The property change listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * Sets the error message and notifies listeners of the change.
     *
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        String oldErrorMessage = this.errorMessage;
        this.errorMessage = errorMessage;
    }

    /**
     * Fires a property change event for the logged in user.
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }


    /**
     * Returns the current error message.
     *
     * @return The current error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
