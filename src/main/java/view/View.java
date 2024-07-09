package view;

import view.view_components.interfaces.ThemedComponent;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public interface View extends ActionListener, PropertyChangeListener, ThemedComponent {

    public String getViewName();
}
