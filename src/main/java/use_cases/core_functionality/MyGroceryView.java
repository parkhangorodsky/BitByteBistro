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


public class MyGroceryView extends View implements ThemeColoredObject, NightModeObject {
    private MyGroceryViewModel viewModel;
    private GroceryInputPanel groceryInputPanel;
    private GroceryOutputPanel groceryOutputPanel;

    public MyGroceryView(MyGroceryViewModel viewModel, AddNewGroceryListController addNewGroceryListController) {
        observeNight();
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
                groceryOutputPanel.updateMyGrocery();
            }
        });

        toggleNightMode();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("init")) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            groceryOutputPanel.updateMyGrocery();
        } else if (evt.getPropertyName().equals("added shopping list")) {
            viewModel.setUser(LoggedUserData.getLoggedInUser());
            groceryOutputPanel.updateMyGrocery();
        } else if (evt.getPropertyName().equals("grocery list already exists")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "This grocery list already exists.",
                    "",
                    JOptionPane.ERROR_MESSAGE);
        } else if (evt.getPropertyName().equals("grocery") || evt.getPropertyName().equals("subtractFridgeFromGrocery")) {
            groceryOutputPanel.updateMyGrocery();
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void setNightMode() {
        this.setBackground(black);
        groceryOutputPanel.setNightMode();
    }

    @Override
    public void setDayMode() {
        this.setBackground(claudeWhite);
        groceryOutputPanel.setDayMode();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
