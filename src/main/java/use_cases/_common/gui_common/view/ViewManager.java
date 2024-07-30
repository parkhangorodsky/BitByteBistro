package use_cases._common.gui_common.view;

import use_cases._common.gui_common.abstractions.View;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Overview: ViewManager class stores all the views associated with the application,
 * and switches between different views in the GUI.
 * It listens to property changes in the ViewManagerModel and updates the displayed view accordingly.
 */
public class ViewManager implements PropertyChangeListener {

    private JPanel views; // Container for all the views
    private CardLayout cardLayout; // Layout manager
    private ViewManagerModel viewManagerModel; // The view model that stores the current view state.

    /**
     * Constructs a new ViewManager with the specified views container, layout manager, and view manager model.
     *
     * @param views            the JPanel that contains the different views
     * @param cardLayout       the CardLayout used to manage the views
     * @param viewManagerModel the model that manages the view state
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Adds a view to the ViewManager.
     *
     * @param view the view to be added
     */
    public void addView(View view) {
        this.views.add((JPanel) view, view.getViewName());
    }

    /**
     * Responds to property change events. If the property name of the property change event is "view change",
     * it updates the displayed view to the new view name specified in the event.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view change")) {
            String newViewName = (String) evt.getNewValue();
            cardLayout.show(views, newViewName);
        } else if (evt.getPropertyName().equals("init")) {
            for (Component component : views.getComponents()) {
                View componentView = (View) component;
                if (componentView.getViewName().equals(evt.getNewValue())) {
                    componentView.propertyChange(evt);
                }
            }
        }
    }
}
