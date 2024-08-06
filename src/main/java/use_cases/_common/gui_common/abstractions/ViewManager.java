package use_cases._common.gui_common.abstractions;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Overview: AbstractViewManager class provides the basic functionalities
 * for managing views using CardLayout and ViewManagerModel.
 */
public abstract class ViewManager implements PropertyChangeListener {
    protected JPanel views;
    protected CardLayout cardLayout; // Layout manager
    private ViewManagerModel viewManagerModel; // The view model that stores the current view state.

    /**
     * Constructs a new AbstractViewManager with the specified layout manager and view manager model.
     *
     * @param cardLayout       the CardLayout used to manage the views
     * @param viewManagerModel the model that manages the view state
     */
    public ViewManager(CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
    }

    public void addView(View view) {
        this.views.add((JPanel) view, view.getViewName());
    }

    public void resetViews() {
        views.removeAll();
    }

    /**
     * Switches to the specified view.
     *
     * @param viewName the name of the view to switch to
     */
    public void showView(String viewName) {
        cardLayout.show(views, viewName);
    }


    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public void propertyChange(PropertyChangeEvent evt){
        if ("view change".equals(evt.getPropertyName())) {
            String newViewName = (String) evt.getNewValue();
            showView(newViewName);
        }
    }
}
