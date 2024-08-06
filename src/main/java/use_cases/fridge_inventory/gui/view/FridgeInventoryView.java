package use_cases.fridge_inventory.gui.view;

import entity.Ingredient;
import use_cases.fridge_inventory.FridgeInventoryViewModel;
import use_cases._common.gui_common.abstractions.View;
import use_cases.fridge_inventory.FridgeInventoryInputBoundary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class FridgeInventoryView extends View {

    private FridgeInventoryViewModel viewModel;
    private JPanel fridgeInventoryContainer;
    private JScrollPane fridgeInventoryScrollPane;
    private FridgeInventoryInputBoundary inputBoundary;

    JTextField foodField;
    JTextField quantityField;
    JTextField unitField;
    JButton addButton;
    JButton removeButton;

    public FridgeInventoryView(FridgeInventoryViewModel viewModel, FridgeInventoryInputBoundary inputBoundary) {
        this.viewModel = viewModel;
        this.inputBoundary = inputBoundary;
        this.setViewName(viewModel.getViewName());

        this.setLayout(new BorderLayout());

        JPanel viewPanel = setUpContentView();

        this.add(viewPanel, BorderLayout.CENTER);
        this.setVisible(true);
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
            String foodName = foodField.getText();
            float quantity = Float.parseFloat(quantityField.getText());
            String unit = unitField.getText();
            viewModel.addOrUpdateIngredient(foodName, quantity, unit);
            updateFridgeInventory(viewModel.getIngredients());
        });


        removeButton.addActionListener(e -> {
            String foodName = foodField.getText();
            float quantity = Float.parseFloat(quantityField.getText());
            viewModel.removeIngredient(foodName, quantity);
            updateFridgeInventory(viewModel.getIngredients());
        });

        // Main container for the inventory list
        fridgeInventoryContainer = new JPanel(new GridBagLayout());
        fridgeInventoryContainer.setBackground(Color.LIGHT_GRAY);

        // Scroll pane for the inventory list
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
        fridgeInventoryContainer.removeAll();

        // Re-add headers
        addHeaderRow();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;

        for (Ingredient ingredient : ingredients) {
            gbc.gridx = 0;
            gbc.weightx = 1;
            JLabel foodLabel = new JLabel(ingredient.getIngredientName(), SwingConstants.CENTER);
            foodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            fridgeInventoryContainer.add(foodLabel, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.5;
            JLabel quantityLabel = new JLabel(String.valueOf(ingredient.getQuantity()), SwingConstants.CENTER);
            quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            fridgeInventoryContainer.add(quantityLabel, gbc);

            gbc.gridx = 2;
            gbc.weightx = 0.5;
            JLabel unitLabel = new JLabel(ingredient.getQuantityUnit(), SwingConstants.CENTER);
            unitLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            fridgeInventoryContainer.add(unitLabel, gbc);

            gbc.gridy++;
        }

        SwingUtilities.invokeLater(() -> fridgeInventoryScrollPane.getVerticalScrollBar().setValue(0));

        fridgeInventoryContainer.revalidate();
        fridgeInventoryContainer.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions (e.g., button clicks)
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("update".equals(evt.getPropertyName())) {
            updateFridgeInventory(viewModel.getIngredients());
        }
    }
}
