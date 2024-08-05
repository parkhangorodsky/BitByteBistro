package use_cases._common.authentication;

import frameworks.gui.GUI;
import frameworks.gui.SwingGUI;
import use_cases._common.interface_adapter_common.view_model.abstractions.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state of the authentication views (login and signup).
 * This class extends the ViewModel class and uses PropertyChangeSupport to notify listeners
 * of changes to its properties.
 */
public class AuthenticationViewModel extends ViewModel {
    private final PropertyChangeSupport support;
    private final GUI gui;

    /**
     * Constructs a new AuthenticationViewModel with the specified view name and SwingGUI instance.
     *
     * @param viewName The name of the view.
     * @param gui The instance of SwingGUI to handle property changes.
     */
    public AuthenticationViewModel(String viewName, GUI gui) {
        super(viewName);  // Call to the parameterized constructor of the superclass
        this.support = new PropertyChangeSupport(this);
        this.gui = gui;
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
     * Fires a property change event.
     *
     * @param propertyName The name of the property.
     * @param oldValue     The old value of the property.
     * @param newValue     The new value of the property.
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }
}
