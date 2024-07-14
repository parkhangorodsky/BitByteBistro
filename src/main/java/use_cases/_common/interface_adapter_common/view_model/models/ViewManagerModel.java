package use_cases._common.interface_adapter_common.view_model.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewManagerModel(){};

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String activeViewName) {
        this.activeViewName = activeViewName;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("view change", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        support.addPropertyChangeListener(propertyChangeListener);
    }
}
