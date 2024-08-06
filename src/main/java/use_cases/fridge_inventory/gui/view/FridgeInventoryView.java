package use_cases.fridge_inventory.gui.view;

import app.local.LocalAppSetting;
import entity.Ingredient;
import use_cases._common.gui_common.abstractions.NightModeObject;
import use_cases._common.gui_common.abstractions.ThemeColoredObject;
import use_cases._common.gui_common.abstractions.View;
import use_cases._common.gui_common.view_components.layouts.VerticalFlowLayout;
import use_cases._common.gui_common.view_components.round_component.RoundButton;
import use_cases._common.gui_common.view_components.round_component.RoundPanel;
import use_cases.fridge_inventory.FridgeInventoryViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class FridgeInventoryView extends View implements ThemeColoredObject, NightModeObject {

    private FridgeInventoryViewModel viewModel;
    private JPanel fridgeInventoryContainer;
    private JScrollPane fridgeInventoryScrollPane;

    JTextField textField;
    JButton searchButton;

    public FridgeInventoryView(FridgeInventoryViewModel viewModel) {

        observeNight();
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.setViewName(viewModel.getViewName());

        this.setLayout(new BorderLayout());

        JPanel viewPanel = setUpContentView();

        toggleNightMode();

        this.add(viewPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks or other actions here
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("update")) {
            updateFridgeInventory(viewModel.getIngredients());
        } else if (evt.getPropertyName().equals("nightMode")) {
            toggleNightMode();
            this.revalidate();
            this.repaint();
        }
    }

    private JPanel setUpContentView() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setPreferredSize(new Dimension(800, 100));

        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 5));

        JPanel outputPanel = new JPanel();
        outputPanel.setOpaque(false);
        outputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        outputPanel.setLayout(new BorderLayout());

        fridgeInventoryContainer = new JPanel(new VerticalFlowLayout(10));
        fridgeInventoryScrollPane = new JScrollPane(fridgeInventoryContainer);
        fridgeInventoryScrollPane.setOpaque(false);
        fridgeInventoryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        fridgeInventoryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fridgeInventoryScrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 0));

        outputPanel.add(fridgeInventoryScrollPane);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private void updateFridgeInventory(List<Ingredient> ingredients) {
        fridgeInventoryContainer.removeAll();

        for (Ingredient ingredient : ingredients) {
            JPanel ingredientItem = createIngredientItem(ingredient);
            fridgeInventoryContainer.add(ingredientItem);
        }
        SwingUtilities.invokeLater(() -> fridgeInventoryScrollPane.getVerticalScrollBar().setValue(0));

        fridgeInventoryContainer.revalidate();
        fridgeInventoryContainer.repaint();
    }

    private JPanel createIngredientItem(Ingredient ingredient) {
        RoundPanel ingredientItem = new RoundPanel();
        ingredientItem.setLayout(new BorderLayout());
        ingredientItem.setBorder(new EmptyBorder(10, 10, 10, 10));
        ingredientItem.setBackground(LocalAppSetting.isNightMode() ? Color.DARK_GRAY : Color.LIGHT_GRAY);

        JPanel ingredientNamePanel = new JPanel(new BorderLayout());
        ingredientNamePanel.setOpaque(false);
        JLabel ingredientNameLabel = new JLabel(ingredient.getIngredientName());
        ingredientNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ingredientNameLabel.setForeground(LocalAppSetting.isNightMode() ? Color.WHITE : Color.BLACK);
        ingredientNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ingredientNameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        ingredientNamePanel.add(ingredientNameLabel, BorderLayout.CENTER);

        ingredientItem.add(ingredientNamePanel, BorderLayout.CENTER);

        return ingredientItem;
    }

    @Override
    public void setNightMode() {
        this.setBackground(Color.BLACK);
        updateFridgeInventory(viewModel.getIngredients());
        fridgeInventoryContainer.setBackground(Color.BLACK);
    }

    @Override
    public void setDayMode() {
        this.setBackground(Color.WHITE);
        updateFridgeInventory(viewModel.getIngredients());
        fridgeInventoryContainer.setBackground(Color.WHITE);
    }
}

