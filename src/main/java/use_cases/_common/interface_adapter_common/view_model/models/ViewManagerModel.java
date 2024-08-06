package use_cases._common.interface_adapter_common.view_model.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Overview: ViewManagerModel stores and updates the state of the active view in a GUI application.
 * It notifies listeners about changes to the active view.
 */
public class ViewManagerModel {
    private String activeViewName; // view name of the active view
    private final PropertyChangeSupport support = new PropertyChangeSupport(this); // Property change manager

    /**
     * Default constructor for ViewManagerModel.
     */
    public ViewManagerModel(){};

    /**
     * Sets the name of the active view.
     *
     * @return the name of the active view
     */
    public void setActiveView(String activeViewName) {
        this.activeViewName = activeViewName;
    }

    /**
     * Get the name of the active view.

     */
    public String getActiveView() {
        return activeViewName;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    /**
     * Fires a property change event to notify listeners that the active view has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view change", null, this.activeViewName);
    }

    public void firePropertyChanged(String propertyName, String viewName) {
        support.firePropertyChange(propertyName, null, viewName);
    }
}
