package use_cases._common.gui_common.abstractions;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public abstract class View extends JPanel implements ActionListener, PropertyChangeListener, ThemeColoredObject {
    private String viewName;
    public String getViewName() {
        return viewName;
    };
}
