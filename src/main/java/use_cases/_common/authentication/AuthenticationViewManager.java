package use_cases._common.authentication;

import use_cases._common.gui_common.abstractions.ViewManager;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuthenticationViewManager extends ViewManager implements PropertyChangeListener {

    /**
     * Constructs a new AbstractViewManager with the specified views container and layout manager.
     *
     * @param views      the JPanel that contains the different views
     * @param cardLayout the CardLayout used to manage the views
     */
    public AuthenticationViewManager(CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        super(cardLayout, viewManagerModel);
    }

    @Override
    public void showView(String viewName) {

    }

}

