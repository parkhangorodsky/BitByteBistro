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
import use_cases._common.interface_adapter_common.presenter.abstractions.PropertyChangeFirer;
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


public class GroceryInputPanel extends JPanel {
    private JTextField newListNameTextField;
    private JButton confirmButton;
    private AddNewGroceryListController addNewGroceryListController;
    private boolean isTextBarOpen = false;
    private MyGroceryViewModel viewModel;

    public GroceryInputPanel(AddNewGroceryListController addNewGroceryListController, MyGroceryViewModel viewModel) {
        this.addNewGroceryListController = addNewGroceryListController;
        this.viewModel = viewModel;
        setUpPanel();
    }

    private void setUpPanel() {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(800, 100));
        this.setMaximumSize(this.getPreferredSize());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        JButton addNewGroceryListButton = new JButton("Make new grocery list...");
        addNewGroceryListButton.addActionListener(e -> showNewGroceryListInput());
        this.add(addNewGroceryListButton);
    }

    private void showNewGroceryListInput() {
        if (isTextBarOpen) return;
        isTextBarOpen = true;

        JLabel promptLabel = new JLabel("Enter grocery list name...");
        newListNameTextField = new JTextField(20);
        newListNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleCreateNewGroceryList();
                }
            }
        });

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> handleCreateNewGroceryList());

        this.add(newListNameTextField);
        this.add(confirmButton);
        this.revalidate();
        this.repaint();
    }

    private void handleCreateNewGroceryList() {
        String newGroceryListName = newListNameTextField.getText().trim();

        if (newGroceryListName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Grocery list name cannot be blank. Please enter a valid name.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            addNewGroceryListController.execute(newGroceryListName, viewModel);
            resetInput();
        }
    }

    private void resetInput() {
        this.removeAll();
        setUpPanel();
        isTextBarOpen = false;
        this.revalidate();
        this.repaint();
    }
}
