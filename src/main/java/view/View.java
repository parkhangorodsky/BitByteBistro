package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public interface View extends ActionListener, PropertyChangeListener {
    public String getViewName();
}
