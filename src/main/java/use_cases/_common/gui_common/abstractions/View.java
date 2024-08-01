package use_cases._common.gui_common.abstractions;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * Overview: Abstract class representing the view. Every view extends {@link JPanel}
 * and implements {@link ActionListener} and {@link PropertyChangeListener}
 */
public abstract class View extends JPanel implements ActionListener, PropertyChangeListener{
    private String viewName; // The name of the view

    /**
     * Getter for the view's name
     * @return the name of the view.
     */
    public String getViewName() {
        return viewName;
    };
    public void setViewName(String viewName) {this.viewName = viewName;};
}
