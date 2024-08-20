package use_cases.core_functionality;

import app.local.LocalAppSetting;
import app.local.LoggedUserData;
import entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.core_functionality.CoreFunctionalityInteractor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;


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
        switch (evt.getPropertyName()) {
            case "init":
                viewModel.setUser(LoggedUserData.getLoggedInUser());
                groceryOutputPanel.updateMyGrocery();
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
