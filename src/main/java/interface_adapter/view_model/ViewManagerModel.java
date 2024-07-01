package interface_adapter.view_model;

import view.ViewManager;

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

    public void addPropertyChangeListener(ViewManager viewManager) {
        support.addPropertyChangeListener(viewManager);
    }
}
