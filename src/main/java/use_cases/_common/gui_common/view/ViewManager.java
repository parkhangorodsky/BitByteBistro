package use_cases._common.gui_common.view;

import use_cases._common.gui_common.abstractions.PopUpView;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases._common.gui_common.abstractions.AbstractViewManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Overview: ViewManager class stores all the views associated with the application,
 * and switches between different views in the GUI.
 * It listens to property changes in the ViewManagerModel and updates the displayed view accordingly.
 */
public class ViewManager extends AbstractViewManager implements PropertyChangeListener {

    private JPanel views; // Container for all the views
    private Map<String, PopUpView> popUpViews;

    /**
     * Constructs a new ViewManager with the specified views container, layout manager, and view manager model.
     *
     * @param views            the JPanel that contains the different views
     * @param cardLayout       the CardLayout used to manage the views
     * @param viewManagerModel the model that manages the view state
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        super(cardLayout, viewManagerModel);
        this.views = views;
        this.getViewManagerModel().addPropertyChangeListener(this);
        this.popUpViews = new HashMap<>();
    }

    @Override
    protected Container getViewsContainer() {
        return views;
    }

    /**
     * Adds a view to the ViewManager.
     *
     * @param view the view to be added
     */
    public void addView(View view) {
        this.views.add((JPanel) view, view.getViewName());
    }

    public void addPopupView(String viewName, PopUpView popUpView) {
        this.popUpViews.put(viewName, popUpView);
    }

    /**
     * Responds to property change events. If the property name of the property change event is "view change",
     * it updates the displayed view to the new view name specified in the event.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("view change".equals(evt.getPropertyName())) {
            String newViewName = (String) evt.getNewValue();
            showView(newViewName);

        } else if (evt.getPropertyName().equals("init")) {
            for (Component component : views.getComponents()) {
                View componentView = (View) component;
                if (componentView.getViewName().equals(evt.getNewValue())) {
                    componentView.propertyChange(evt);
                }
            }
        } else if (evt.getPropertyName().equals("pop up")) {
            handlePopUpRequest((String) evt.getNewValue());
        }
    }

    private void handlePopUpRequest(String viewName) {
        PopUpView popUpView = popUpViews.get(viewName);
        if (popUpView != null) {
            popUpView.showPopUp();
        }
    }
}
