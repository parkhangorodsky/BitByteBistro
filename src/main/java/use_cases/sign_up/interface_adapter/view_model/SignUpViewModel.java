package use_cases.sign_up.interface_adapter.view_model;

import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state of the sign-up view.
 * This class extends the ViewModel class and uses PropertyChangeSupport to notify listeners
 * of changes to its properties, specifically the userID, userEmail, successMessage, and errorMessage properties.
 */
public class SignUpViewModel extends ViewModel {
    private String userID;
    private String userEmail;
    private String successMessage;
    private String errorMessage;
    private final PropertyChangeSupport support;

    /**
     * Constructs a new SignUpViewModel with the specified view name.
     *
     * @param viewName The name of the view.
     */
    public SignUpViewModel(String viewName) {
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
     * Returns the current user ID.
     *
     * @return The current user ID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the user ID and notifies listeners of the change.
     *
     * @param userID The user ID to set.
     */
    public void setUserID(String userID) {
        String oldUserID = this.userID;
        this.userID = userID;
        support.firePropertyChange("userID", oldUserID, userID);
    }

    /**
     * Returns the current user email.
     *
     * @return The current user email.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the user email and notifies listeners of the change.
     *
     * @param userEmail The user email to set.
     */
    public void setUserEmail(String userEmail) {
        String oldUserEmail = this.userEmail;
        this.userEmail = userEmail;
        support.firePropertyChange("userEmail", oldUserEmail, userEmail);
    }

    /**
     * Returns the current success message.
     *
     * @return The current success message.
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the success message and notifies listeners of the change.
     *
     * @param successMessage The success message to set.
     */
    public void setSuccessMessage(String successMessage) {
        String oldSuccessMessage = this.successMessage;
        this.successMessage = successMessage;
        support.firePropertyChange("successMessage", oldSuccessMessage, successMessage);
    }

    /**
     * Returns the current error message.
     *
     * @return The current error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message and notifies listeners of the change.
     *
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        String oldErrorMessage = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldErrorMessage, errorMessage);
    }
}
