package use_cases.core_functionality;

import app.local.LoggedUserData;

import java.awt.*;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.view_components.GroceryInputPanel;
import use_cases.core_functionality.view_components.GroceryOutputPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;

/**
 * The `MyGroceryView` class represents the view for managing grocery lists. It integrates with
 * the `MyGroceryViewModel` and `AddNewGroceryListController` to provide functionalities for
 * adding new grocery lists and displaying existing ones. The view supports night mode and
 * day mode themes.
 */
public class MyGroceryView extends View implements ThemeColoredObject, NightModeObject {
    private MyGroceryViewModel viewModel;
    private GroceryInputPanel groceryInputPanel;
    private GroceryOutputPanel groceryOutputPanel;

    /**
     * Constructs a `MyGroceryView` object with the specified view model and controller.
     *
     * @param viewModel                  The view model that contains data and state for the view.
     * @param addNewGroceryListController The controller for adding new grocery lists.
     */
    public MyGroceryView(MyGroceryViewModel viewModel, AddNewGroceryListController addNewGroceryListController) {
        observeNight();  // Initialize night mode observer
        this.viewModel = viewModel;
        this.setLayout(new BorderLayout());
        this.setViewName(viewModel.getViewName());
        this.viewModel.addPropertyChangeListener(this);

        // Initialize panels
        groceryInputPanel = new GroceryInputPanel(addNewGroceryListController, viewModel);
        groceryOutputPanel = new GroceryOutputPanel(viewModel);

        // Set up content view
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setOpaque(false);
        viewPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        viewPanel.add(groceryInputPanel, BorderLayout.NORTH);
        viewPanel.add(groceryOutputPanel, BorderLayout.CENTER);

        this.add(viewPanel, BorderLayout.CENTER);
        this.setVisible(true);

        viewModel.setUser(LoggedUserData.getLoggedInUser());  // Ensure user data is set
        groceryOutputPanel.updateMyGrocery();  // Display existing grocery lists

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                groceryOutputPanel.updateMyGrocery();  // Update grocery list when the view is shown
            }
        });

        toggleNightMode();  // Set initial theme mode
    }

    /**
     * Handles property change events from the view model.
     * Updates the grocery list display and manages theme changes.
     *
     * @param evt The property change event that indicates a change in the view model's state.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "init":
            case "added shopping list":
                viewModel.setUser(LoggedUserData.getLoggedInUser());
                groceryOutputPanel.updateMyGrocery();
                break;
            case "grocery list already exists":
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                        "This grocery list already exists.",
                        "",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case "grocery":
            case "subtractFridgeFromGrocery":
                groceryOutputPanel.updateMyGrocery();
                break;
            case "nightMode":
                toggleNightMode();
                this.revalidate();
                this.repaint();
                break;
        }
    }

    /**
     * Sets the view to night mode theme.
     */
    @Override
    public void setNightMode() {
        this.setBackground(black);
        groceryOutputPanel.setNightMode();
    }

    /**
     * Sets the view to day mode theme.
     */
    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        groceryOutputPanel.setDayMode();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // No action handling required for this view
    }
}
