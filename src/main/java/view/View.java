package view;

import view.view_components.ViewComponent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public interface View extends ActionListener, PropertyChangeListener, ViewComponent {

    public String getViewName();
}
