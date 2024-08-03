package use_cases._common.gui_common.abstractions;

import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;

import java.awt.*;

/**
 * Overview: AbstractViewManager class provides the basic functionalities
 * for managing views using CardLayout and ViewManagerModel.
 */
public abstract class AbstractViewManager {

    private CardLayout cardLayout; // Layout manager
    private ViewManagerModel viewManagerModel; // The view model that stores the current view state.

    /**
     * Constructs a new AbstractViewManager with the specified layout manager and view manager model.
     *
     * @param cardLayout       the CardLayout used to manage the views
     * @param viewManagerModel the model that manages the view state
     */
    public AbstractViewManager(CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Switches to the specified view.
     *
     * @param viewName the name of the view to switch to
     */
    public void showView(String viewName) {
        cardLayout.show(getViewsContainer(), viewName);
    }

    protected abstract Container getViewsContainer();

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
