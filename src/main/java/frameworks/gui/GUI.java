package frameworks.gui;

import app.config.Config;
import frameworks.gui.view_factory.ViewFactory;

import java.beans.PropertyChangeListener;

public abstract class GUI implements PropertyChangeListener {
    ViewFactory viewFactory;
    public abstract void initialize(Config config);
}
