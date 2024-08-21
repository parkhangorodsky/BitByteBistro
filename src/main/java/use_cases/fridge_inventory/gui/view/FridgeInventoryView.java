package use_cases.fridge_inventory.gui.view;

import entity.Ingredient;
import use_cases.fridge_inventory.FridgeInventoryViewModel;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.abstractions.NightModeObject;
import app.local.LocalAppSetting;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import use_cases.fridge_inventory.FridgeInventoryController;

public class FridgeInventoryView extends View implements NightModeObject {

    private FridgeInventoryViewModel viewModel;
    JPanel fridgeInventoryContainer;
    private JScrollPane fridgeInventoryScrollPane;
    private FridgeInventoryController controller;

    JTextField foodField;
    JTextField quantityField;
    JTextField unitField;
    JButton addButton;
    JButton removeButton;

    public FridgeInventoryView(FridgeInventoryViewModel viewModel, FridgeInventoryController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.setViewName(viewModel.getViewName());
        this.viewModel.addPropertyChangeListener(this);

        observeNight();  // Observe night mode changes
        JPanel viewPanel = setUpContentView();
        this.add(viewPanel, BorderLayout.CENTER);
        this.setVisible(true);

        toggleNightMode();  // Apply initial night mode setting
    }

    private JPanel setUpContentView() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Input panel for adding/removing ingredients
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        foodField = new JTextField(10);
        quantityField = new JTextField(5);
        unitField = new JTextField(5);
        addButton = new JButton("+");
        removeButton = new JButton("-");

        inputPanel.add(new JLabel("Food"));
        inputPanel.add(foodField);
        inputPanel.add(new JLabel("Quantity"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Unit"));
        inputPanel.add(unitField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        addButton.setEnabled(true);
        removeButton.setEnabled(true);

        // Action listeners for buttons
        addButton.addActionListener(e -> {
            String foodName = foodField.getText().trim();
            String quantityText = quantityField.getText().trim();
            String unit = unitField.getText().trim();

            // Check if any of the fields are empty
            if (foodName.isEmpty() || quantityText.isEmpty() || unit.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields before adding an item.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Parse the quantity to float
                float quantity = Float.parseFloat(quantityText);
                controller.addIngredient(foodName, quantity, unit, ""); // Use the controller to add ingredient
            } catch (NumberFormatException ex) {
                // Handle the exception gracefully
                JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });


        removeButton.addActionListener(e -> {
            try {
                String foodName = foodField.getText();
                float quantity = Float.parseFloat(quantityField.getText());
                String unit = unitField.getText();
                // Use the controller to remove ingredient
                controller.removeIngredient(foodName, quantity, unit);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Main container for the inventory list
        fridgeInventoryContainer = new JPanel(new GridBagLayout());
        fridgeInventoryScrollPane = new JScrollPane(fridgeInventoryContainer);
        fridgeInventoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        fridgeInventoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(fridgeInventoryScrollPane, BorderLayout.CENTER);

        // Add headers initially
        updateFridgeInventory(viewModel.getIngredients());

        return mainPanel;
    }

    private void addHeaderRow() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel foodHeader = new JLabel("Food", SwingConstants.CENTER);
        JLabel quantityHeader = new JLabel("Quantity", SwingConstants.CENTER);
        JLabel unitHeader = new JLabel("Unit", SwingConstants.CENTER);
        foodHeader.setFont(new Font("Arial", Font.BOLD, 16));
        quantityHeader.setFont(new Font("Arial", Font.BOLD, 16));
        unitHeader.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        fridgeInventoryContainer.add(foodHeader, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.5;
        fridgeInventoryContainer.add(quantityHeader, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.5;
        fridgeInventoryContainer.add(unitHeader, gbc);
    }

    private void updateFridgeInventory(List<Ingredient> ingredients) {
        fridgeInventoryContainer.removeAll(); // Clear the existing components

        if (ingredients == null || ingredients.isEmpty()) {
            JLabel emptyLabel = new JLabel("Fridge is empty.");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            emptyLabel.setForeground(LocalAppSetting.isNightMode() ? Color.WHITE : Color.BLACK); // Ensure the label color matches the mode
            fridgeInventoryContainer.add(emptyLabel);
        } else {
            addHeaderRow(); // Add headers

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridy = 1;

            for (Ingredient ingredient : ingredients) {
                gbc.gridx = 0;
                gbc.weightx = 1;
                JLabel foodLabel = new JLabel(ingredient.getIngredientName(), SwingConstants.CENTER);
                foodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                foodLabel.setForeground(LocalAppSetting.isNightMode() ? Color.WHITE : Color.BLACK);
                fridgeInventoryContainer.add(foodLabel, gbc);

                gbc.gridx = 1;
                gbc.weightx = 0.5;
                JLabel quantityLabel = new JLabel(String.valueOf(ingredient.getQuantity()), SwingConstants.CENTER);
                quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                quantityLabel.setForeground(LocalAppSetting.isNightMode() ? Color.WHITE : Color.BLACK);
                fridgeInventoryContainer.add(quantityLabel, gbc);

                gbc.gridx = 2;
                gbc.weightx = 0.5;
                JLabel unitLabel = new JLabel(ingredient.getQuantityUnit(), SwingConstants.CENTER);
                unitLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                unitLabel.setForeground(LocalAppSetting.isNightMode() ? Color.WHITE : Color.BLACK);
                fridgeInventoryContainer.add(unitLabel, gbc);

                gbc.gridy++;
            }
        }

        // Revalidate and repaint the container
        fridgeInventoryContainer.revalidate();
        fridgeInventoryContainer.repaint();

        // Ensure the entire view is revalidated and repainted
        this.revalidate();
        this.repaint();

        // Reapply night mode to ensure colors are correct
        toggleNightMode();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions (e.g., button clicks)
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("update".equals(evt.getPropertyName()) || "ingredients".equals(evt.getPropertyName())) {
            List<Ingredient> ingredients = viewModel.getIngredients();
            updateFridgeInventory(ingredients);

            // Force the entire component to revalidate and repaint
            this.revalidate();
            this.repaint();
        } else if ("nightMode".equals(evt.getPropertyName())) {
            toggleNightMode();
        }
    }

    @Override
    public void setNightMode() {
        this.setBackground(Color.BLACK);
        fridgeInventoryContainer.setBackground(Color.DARK_GRAY);

        // Set label and text field colors to white in night mode
        foodField.setForeground(Color.WHITE);
        foodField.setBackground(Color.BLACK);

        quantityField.setForeground(Color.WHITE);
        quantityField.setBackground(Color.BLACK);

        unitField.setForeground(Color.WHITE);
        unitField.setBackground(Color.BLACK);

        // Set label colors to white
        JLabel foodLabel = (JLabel) foodField.getParent().getComponent(0);
        JLabel quantityLabel = (JLabel) quantityField.getParent().getComponent(2);
        JLabel unitLabel = (JLabel) unitField.getParent().getComponent(4);

        foodLabel.setForeground(Color.BLACK);
        quantityLabel.setForeground(Color.BLACK);
        unitLabel.setForeground(Color.BLACK);

        // Update header row labels to white in night mode
        updateHeaderRowColor(Color.WHITE);

        // Update button colors if needed
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);

        removeButton.setForeground(Color.WHITE);
        removeButton.setBackground(Color.BLACK);
    }

    private void updateHeaderRowColor(Color color) {
        // Assuming these components are part of fridgeInventoryContainer, set their colors
        Component[] components = fridgeInventoryContainer.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                ((JLabel) component).setForeground(color);
            }
        }
    }

    @Override
    public void setDayMode() {
        this.setBackground(Color.WHITE);
        fridgeInventoryContainer.setBackground(Color.LIGHT_GRAY);
        foodField.setBackground(Color.WHITE);
        foodField.setForeground(Color.BLACK);
        quantityField.setBackground(Color.WHITE);
        quantityField.setForeground(Color.BLACK);
        unitField.setBackground(Color.WHITE);
        unitField.setForeground(Color.BLACK);
        addButton.setBackground(Color.WHITE);
        addButton.setForeground(Color.BLACK);
        removeButton.setBackground(Color.WHITE);
        removeButton.setForeground(Color.BLACK);

        // Update the labels in the input panel to be black
        for (Component component : foodField.getParent().getComponents()) {
            if (component instanceof JLabel) {
                ((JLabel) component).setForeground(Color.BLACK);
            }
        }

        // Update all labels inside fridgeInventoryContainer to be black
        updateTextColor(Color.BLACK);
    }


    private void updateInputPanelColors(Color textColor, Color bgColor) {
        foodField.setForeground(textColor);
        foodField.setBackground(bgColor);
        quantityField.setForeground(textColor);
        quantityField.setBackground(bgColor);
        unitField.setForeground(textColor);
        unitField.setBackground(bgColor);
        addButton.setForeground(textColor);
        addButton.setBackground(bgColor);
        removeButton.setForeground(textColor);
        removeButton.setBackground(bgColor);
    }

    private void updateTextColor(Color color) {
        for (Component component : fridgeInventoryContainer.getComponents()) {
            if (component instanceof JLabel) {
                ((JLabel) component).setForeground(color);
            }
        }
    }
}

