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
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
            viewModel.addIngredient(foodName, quantity, unit);
            updateFridgeInventory(viewModel.getIngredients());
        });

        removeButton.addActionListener(e -> {
            String foodName = foodField.getText();
            viewModel.removeIngredient(foodName);
            updateFridgeInventory(viewModel.getIngredients());
        });

        // Output panel for displaying ingredients
        fridgeInventoryContainer = new JPanel();
        fridgeInventoryContainer.setLayout(new BoxLayout(fridgeInventoryContainer, BoxLayout.Y_AXIS));

        // Adding headers
        JPanel headerPanel = new JPanel(new GridLayout(1, 3));
        headerPanel.add(new JLabel("Food"));
        headerPanel.add(new JLabel("Quantity"));
        headerPanel.add(new JLabel("Unit"));
        fridgeInventoryContainer.add(headerPanel);

        fridgeInventoryScrollPane = new JScrollPane(fridgeInventoryContainer);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(fridgeInventoryScrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private void updateFridgeInventory(List<Ingredient> ingredients) {
        fridgeInventoryContainer.removeAll();

        // Re-add headers
        JPanel headerPanel = new JPanel(new GridLayout(1, 3));
        headerPanel.add(new JLabel("Food"));
        headerPanel.add(new JLabel("Quantity"));
        headerPanel.add(new JLabel("Unit"));
        fridgeInventoryContainer.add(headerPanel);

        for (Ingredient ingredient : ingredients) {
            JPanel ingredientItem = new JPanel(new GridLayout(1, 3));
            ingredientItem.add(new JLabel(ingredient.getIngredientName()));
            ingredientItem.add(new JLabel(String.valueOf(ingredient.getQuantity())));
            ingredientItem.add(new JLabel(ingredient.getQuantityUnit()));
            fridgeInventoryContainer.add(ingredientItem);
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
        // Handle property changes
    }
}
